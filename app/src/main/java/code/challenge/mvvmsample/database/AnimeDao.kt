package code.challenge.mvvmsample.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import code.challenge.mvvmsample.model.AnimeContent

@Dao
interface AnimeDao {

    @Query("SELECT * FROM Anime")
    fun getAllAnime() : LiveData<List<AnimeContent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAnime(animeList: Array<AnimeContent>)

    @Query("DELETE FROM Anime")
    fun deleteAllAnime()
}