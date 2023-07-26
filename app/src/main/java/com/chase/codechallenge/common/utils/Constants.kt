package com.chase.codechallenge.common.utils

object AppStrings {

    // HomeScreen -> WeatherDetailSection
    const val degree = "°"

    // SearchScreen
    const val home_topbar_title = "Current Weather"
    const val search_topbar_title = "Search by City"

    // SearchScreen -> SearchField
    const val placeholder = "Enter city name"

    // SearchScreen -> CityWeatherList
    const val subtitle1 = "My Cities"
    const val subtitle2 = "Search Result"
    const val emptyScreen = "Search for get city Weather report"

    // SearchCityScreen -> SearchCityScreenContent
    const val error_title = "OOOOPS!!!"
}

object NetworkService {
    const val BASE_URL: String = "https://api.openweathermap.org"
    const val API_KEY: String = "cbaa8c55a43b74a9cd27eb93f2baa9dc"
    const val UNITS: String = "metric"
    const val FORECAST_END_POINT = "/data/2.5/forecast"
}

object Database {
    const val forecast_table = "forecast_data"
    const val database_name = "weather_data.db"
    const val city_table = "city_data"
    const val my_city_table = "my_city"
}

object Constants {
    const val UNKNOWN_ERROR = "An unknown error occurred."
    const val FILL_FIELD = "Please fill in the field."
    const val UNKNOWN_HOST =
        "Unable to resolve host \"api.openweathermap.org\": No address associated with hostname"
}

object ErrorMessages {
    const val GPS_DISABLED = "GPS Disabled"
    const val NO_PERMISSION = "No Permission"
    const val NO_INTERNET_CONNECTION = "No Internet Connection"
    const val UNKNOWN_ERROR = "Unknown Error"
}

