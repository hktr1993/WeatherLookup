package code.challenge.mvvmsample.utils

import android.app.Application
import androidx.room.Room
import code.challenge.mvvmsample.database.AnimeDatabase
import code.challenge.mvvmsample.database.CharacterDatabase

class AnimeApplication : Application() {
    companion object {
        var animeDatabase: AnimeDatabase? = null
        var characterDatabase: CharacterDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        // Side note: I am aware of how to migrate databases in Room, but for this example project, this was not necessary
        animeDatabase = Room.databaseBuilder(applicationContext, AnimeDatabase::class.java, "anime_db").fallbackToDestructiveMigration().build()
        characterDatabase = Room.databaseBuilder(applicationContext, CharacterDatabase::class.java, "character_db").fallbackToDestructiveMigration().build()
    }
}