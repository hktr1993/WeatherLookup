package code.challenge.mvvmsample.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import code.challenge.mvvmsample.model.CharacterContent

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Characters")
    fun getAllCharacters() : LiveData<List<CharacterContent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(animeList: Array<CharacterContent>)

    @Query("DELETE FROM Characters")
    fun deleteAllCharacters()
}