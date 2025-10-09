package nz.ac.canterbury.seng303.busbuddy.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import nz.ac.canterbury.seng303.busbuddy.data.RoutePreferences
import nz.ac.canterbury.seng303.busbuddy.data.Stop
import kotlin.math.*

@SuppressLint("StaticFieldLeak")
class RoutesViewModel(private val context: Context) : ViewModel() {

    private val _selectedRoute = MutableStateFlow<String?>(null)
    val selectedRoute: StateFlow<String?> = _selectedRoute.asStateFlow()

    private val _routeStops = MutableStateFlow<Map<String, List<Stop>>>(emptyMap())
    val routeStops: StateFlow<Map<String, List<Stop>>> = _routeStops

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    init {
        viewModelScope.launch {
            RoutePreferences.getRoute(context).collect { saved ->
                _selectedRoute.value = saved
            }
        }

        // Load stops asynchronously
        viewModelScope.launch {
            val stopsMap = loadRouteStopsAsync(context)
            _routeStops.value = stopsMap
            _loading.value = false
        }
    }

    fun selectRoute(routeName: String) {
        _selectedRoute.value = routeName
        viewModelScope.launch {
            RoutePreferences.saveRoute(context, routeName)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun loadRouteStopsAsync(context: Context): Map<String, List<Stop>> =
        withContext(Dispatchers.IO) {
            context.assets.open("routes_with_stops.json").use { input ->
                Json.decodeFromStream(
                    MapSerializer(String.serializer(), ListSerializer(Stop.serializer())),
                    input
                )
            }
        }

    fun getNextStop(userLat: Double, userLon: Double, maxDistanceMeters: Double = 15000.0): Stop? {
        val routeName = _selectedRoute.value ?: return null
        val stops = _routeStops.value[routeName] ?: return null
        val closest = stops.minByOrNull { distanceMeters(userLat, userLon, it.lat, it.lon) }
        return if (closest != null && distanceMeters(userLat, userLon, closest.lat, closest.lon) <= maxDistanceMeters) {
            closest
        } else {
            null
        }
    }

    fun deg2rad(deg: Double): Double = deg * (Math.PI / 180.0)

    fun distanceMeters(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0
        val dLat = deg2rad(lat2 - lat1)
        val dLon = deg2rad(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) +
                cos(deg2rad(lat1)) * cos(deg2rad(lat2)) *
                sin(dLon / 2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }
}
