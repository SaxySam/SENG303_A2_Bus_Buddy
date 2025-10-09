package nz.ac.canterbury.seng303.busbuddy.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import android.content.Context
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

    init {
        viewModelScope.launch {
            RoutePreferences.getRoute(context)
                .collect { saved ->
                    _selectedRoute.value = saved
                }
        }
    }

    fun selectRoute(routeName: String) {
        _selectedRoute.value = routeName
        viewModelScope.launch {
            RoutePreferences.saveRoute(context, routeName)
        }
    }

    init {
        viewModelScope.launch {
            val stopsMap = loadRouteStops(context)
            _routeStops.value = stopsMap
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun loadRouteStops(context: Context): Map<String, List<Stop>> {
        context.assets.open("routes_with_stops.json").use { input ->
            return Json.decodeFromStream(
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
        val d = R * c
        return d
    }
}
