package code.challenge.mvvmsample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import code.challenge.mvvmsample.model.CharacterContent

@Database(entities = [(CharacterContent::class)], version = 1)
abstract class CharacterDatabase : RoomDatabase(){

    abstract fun characterDao() : CharacterDao
}


