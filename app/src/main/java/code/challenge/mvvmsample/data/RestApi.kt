package code.challenge.mvvmsample.data

import retrofit2.Call
import retrofit2.http.GET
import code.challenge.mvvmsample.model.MainModel
import code.challenge.mvvmsample.model.WeatherResponse
import retrofit2.http.Query

interface RestApi {

    @GET("forecast")
    fun getForecast(@Query("q") cityQuery: String?) : Call<WeatherResponse>
}