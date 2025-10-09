package nz.ac.canterbury.seng303.busbuddy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import android.content.Context
import nz.ac.canterbury.seng303.busbuddy.data.RoutePreferences

class RoutesViewModel(private val context: Context) : ViewModel() {

    private val _selectedRoute = MutableStateFlow<String?>(null)
    val selectedRoute: StateFlow<String?> = _selectedRoute.asStateFlow()

    init {
        // Load saved route from DataStore
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
}
