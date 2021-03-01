package code.challenge.mvvmsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import code.challenge.mvvmsample.data.RestApi
import code.challenge.mvvmsample.model.WeatherResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastActivityViewModel : ViewModel() {

    val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val _forecastData = MutableLiveData<WeatherResponse>()
    val forecastData : LiveData<WeatherResponse> = _forecastData

    fun getWeatherFromAPI(character : String)
    {
        val gson = Gson()
        val okHttpBuilder = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalURL = chain.request().url()
            val url = originalURL.newBuilder().addQueryParameter("appid", "65d00499677e59496ca2f318eb68c049").build()
            request.url(url)
            val response = chain.proceed(request.build())
            return@addInterceptor response
        }
        val retrofit =  Retrofit.Builder()
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()

        val restApi = retrofit.create(RestApi::class.java)

        restApi.getForecast(character).enqueue(object : Callback<WeatherResponse> {

            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                _forecastData.value = response?.body()!!

            }
        })
    }
}