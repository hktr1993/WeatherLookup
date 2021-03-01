package code.challenge.mvvmsample.model

import android.opengl.Visibility

data class ContentModel(
    var dt: Int,
    var main: MainModel,
    var weather: Array<WeatherModel>,
    var clouds: CloudsModel,
    var wind: WindModel,
    var visibility: Int,
    var pop: Double,
    var dt_txt: String
)