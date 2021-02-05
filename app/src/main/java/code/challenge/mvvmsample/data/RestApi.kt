package code.challenge.mvvmsample.data

import retrofit2.Call
import retrofit2.http.GET
import code.challenge.mvvmsample.model.AnimeModel
import code.challenge.mvvmsample.model.CharacterModel
import retrofit2.http.Query

interface RestApi {

    @GET("search/anime?q=naruto")
    fun getAnime() : Call<AnimeModel>

    @GET("search/character")
    fun getCharacters(@Query("q") charQuery: String?) : Call<CharacterModel>
}