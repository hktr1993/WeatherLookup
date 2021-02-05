package code.challenge.mvvmsample.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import code.challenge.mvvmsample.model.CharacterContent

class SearchActivityViewModel : ViewModel() {

    var searchActivityRepository: SearchActivityRepository = SearchActivityRepository()

    fun getAllCharactersList(): LiveData<List<CharacterContent>>
    {
        return searchActivityRepository.getCharacters()
    }

    fun getCharactersFromAPIAndStore(character : String)
    {
        searchActivityRepository.ApiCallAndPutInDB(character)
    }


}