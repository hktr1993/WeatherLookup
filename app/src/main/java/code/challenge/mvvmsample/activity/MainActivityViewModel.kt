package code.challenge.mvvmsample.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import code.challenge.mvvmsample.model.AnimeContent

class MainActivityViewModel : ViewModel() {

    var mainActivityRepository: MainActivityRepository = MainActivityRepository()

    fun getAllAnimeList(): LiveData<List<AnimeContent>>
    {
        return mainActivityRepository.getAnime()
    }

    fun getAnimeFromAPIAndStore()
    {
        mainActivityRepository.ApiCallAndPutInDB()
    }


}