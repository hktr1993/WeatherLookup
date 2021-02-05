package code.challenge.mvvmsample.activity

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import code.challenge.mvvmsample.model.AnimeModel
import code.challenge.mvvmsample.model.AnimeContent
import code.challenge.mvvmsample.data.RestApi
import code.challenge.mvvmsample.utils.AnimeApplication

class MainActivityRepository {

    val BASE_URL = "https://api.jikan.moe/v3/"
    val TAG = MainActivityRepository::class.java.simpleName

    fun getAnime() : LiveData<List<AnimeContent>>
    {
        return AnimeApplication.animeDatabase!!.animeDao().getAllAnime()

    }

    fun ApiCallAndPutInDB()
    {
        val gson = Gson()
        val retrofit =  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

        val restApi = retrofit.create(RestApi::class.java)

        restApi.getAnime().enqueue(object : Callback<AnimeModel>{

            override fun onFailure(call: Call<AnimeModel>?, t: Throwable?) {
                Log.e(TAG,"OOPS!! something went wrong..")
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<AnimeModel>?, response: Response<AnimeModel>?) {

                Log.e(TAG,response!!.body().toString())
                when(response.code())
                {
                    200 ->{
                        Thread {
                            AnimeApplication.animeDatabase!!.animeDao().deleteAllAnime()
                            AnimeApplication.animeDatabase!!.animeDao().insertAllAnime(response.body()!!.results)
                        }.start()
                    }
                }

            }
        })


    }
}