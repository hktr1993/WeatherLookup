package code.challenge.mvvmsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Anime")
data class AnimeContent(
        @PrimaryKey
        var mal_id : Int,
        var url : String,
        var image_url : String,
        var title: String,
        var airing: Boolean,
        var synopsis: String,
        var type : String,
        var episodes : Int,
        var score : Double,
        var start_date : String?,
        var end_date : String?,
        var members : Int,
        var rated : String?
)
