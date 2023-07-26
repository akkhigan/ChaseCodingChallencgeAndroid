package com.chase.codechallenge.common.utils

object AppStrings {

    // HomeScreen -> WeatherDetailSection
    const val temp = "🌡 TEMP"
    const val cloudiness = "☁ CLOUDINESS"
    const val sunrise = "🌇 SUNRISE"
    const val sunset = "🌆 SUNSET"
    const val degree = "°"

    // SearchScreen
    const val home_topbar_title = "Current Location Weather"
    const val search_topbar_title = "Search by City"

    // SearchScreen -> SearchField
    const val placeholder = "Search for a city"

    // SearchScreen -> CityWeatherList
    const val subtitle1 = "My Cities"
    const val subtitle2 = "Search Result"
    const val no_city = "You don't have any city"

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

object ExceptionTitles {
    const val GPS_DISABLED = "GPS Disabled"
    const val NO_PERMISSION = "No Permission"
    const val NO_INTERNET_CONNECTION = "No Internet Connection"
    const val UNKNOWN_ERROR = "Unknown Error"
}

object ExceptionDescriptions {
    const val GPS_DISABLED_DESCR = "Your GPS seems to be disabled, please enable it."
    const val NO_PERMISSION_DESCR = "Allow otherwise location tracking won't work."
    const val NO_INTERNET_CONNECTION_DESCR = "Please check your internet connection."
    const val UNKNOWN_ERROR_DESCR = "Something went wrong."
}

object ErrorCardConsts {
    const val BUTTON_TEXT = "OK"
}


object HourConverter {
    fun convertHour(hour: String): String {
        return if (hour.toInt() in 1..11) {
            "$hour AM"
        } else {
            when (hour) {
                "00" -> {
                    "12 AM"
                }
                "12" -> {
                    "12 PM"
                }
                else -> {
                    "0${hour.toInt() - 12} PM"
                }
            }
        }
    }
}