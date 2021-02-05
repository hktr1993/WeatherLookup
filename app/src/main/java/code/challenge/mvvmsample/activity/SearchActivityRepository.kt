package code.challenge.mvvmsample.activity

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import code.challenge.mvvmsample.data.RestApi
import code.challenge.mvvmsample.model.CharacterContent
import code.challenge.mvvmsample.model.CharacterModel
import code.challenge.mvvmsample.utils.AnimeApplication

class SearchActivityRepository {

    val BASE_URL = "https://api.jikan.moe/v3/"
    val TAG = SearchActivityRepository::class.java.simpleName

    fun getCharacters() : LiveData<List<CharacterContent>>
    {
        return AnimeApplication.characterDatabase!!.characterDao().getAllCharacters()

    }

    fun ApiCallAndPutInDB(character : String)
    {
        val gson = Gson()
        val retrofit =  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

        val restApi = retrofit.create(RestApi::class.java)

        restApi.getCharacters(character).enqueue(object : Callback<CharacterModel>{

            override fun onFailure(call: Call<CharacterModel>?, t: Throwable?) {
                Log.e(TAG,"OOPS!! something went wrong..")
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<CharacterModel>?, response: Response<CharacterModel>?) {

                Log.e(TAG,response!!.body().toString())
                when(response.code())
                {
                    200 ->{
                        Thread {
                            AnimeApplication.characterDatabase!!.characterDao().deleteAllCharacters()
                            AnimeApplication.characterDatabase!!.characterDao().insertAllCharacters(response.body()!!.results)
                        }.start()
                    }
                }

            }
        })


    }
}