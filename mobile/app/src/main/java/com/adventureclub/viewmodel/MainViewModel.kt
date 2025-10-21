class MainViewModel : ViewModel() {
    private val _routes = MutableLiveData<List<Route>>()
    val routes: LiveData<List<Route>> = _routes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadRoutes() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRoutes()
                if (response.isSuccessful) {
                    _routes.value = response.body()
                } else {
                    _error.value = "Ошибка загрузки данных"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка сети: ${e.message}"
            }
        }
    }

    fun addRoute(route: Route) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.createRoute(route)
                if (response.isSuccessful) {
                    loadRoutes()
                } else {
                    _error.value = "Ошибка добавления маршрута"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка сети: ${e.message}"
            }
        }
    }

    fun deleteRoute(routeId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.deleteRoute(routeId)
                if (response.isSuccessful) {
                    loadRoutes()
                } else {
                    _error.value = "Ошибка удаления маршрута"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка сети: ${e.message}"
            }
        }
    }
}