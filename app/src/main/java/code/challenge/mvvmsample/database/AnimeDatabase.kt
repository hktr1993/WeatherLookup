package code.challenge.mvvmsample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import code.challenge.mvvmsample.model.AnimeContent

@Database(entities = [(AnimeContent::class)], version = 1)
abstract class AnimeDatabase : RoomDatabase(){

    abstract fun animeDao() : AnimeDao
}


