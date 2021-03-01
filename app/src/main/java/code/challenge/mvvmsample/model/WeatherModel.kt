package code.challenge.mvvmsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class WeatherModel(
        var id: Int,
        var main: String,
        var description: String,
        var icon: String
)
