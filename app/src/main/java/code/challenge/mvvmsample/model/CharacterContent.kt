package code.challenge.mvvmsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
data class CharacterContent(
        @PrimaryKey
        var mal_id : Int,
        var url : String,
        var image_url : String,
        var name: String,
)
