package code.challenge.mvvmsample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import code.challenge.mvvmsample.adapters.ResultsRecyclerViewAdapter
import code.challenge.mvvmsample.databinding.ActivityForecastBinding
import code.challenge.mvvmsample.model.ContentModel
import code.challenge.mvvmsample.utils.ItemClickSupport
import code.challenge.mvvmsample.utils.NetworkAvailability

class ForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForecastBinding
    lateinit var forecastActivityViewModel: ForecastActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set up the provider for the view model
        forecastActivityViewModel = ViewModelProvider(this).get(ForecastActivityViewModel::class.java)

        if(NetworkAvailability().isNetworkConnected(this))
        {
            forecastActivityViewModel.getWeatherFromAPI(intent.extras?.getString("city")!!)
        }
        else
        {
            Toast.makeText(this,"No internet found.",Toast.LENGTH_LONG).show()
        }


        // Observes the character list data for the recycler view
        forecastActivityViewModel.forecastData.observe(this, { weatherList ->
            setUpRecyclerView(weatherList!!.list)
        })

        ItemClickSupport.addTo(binding.searchResults).setOnItemClickListener{recyclerView, position, v ->
            val fragment = DetailFragment.newInstance(position)
            fragment.show(supportFragmentManager, fragment.tag)
        }
    }

    private fun setUpRecyclerView(weather : Array<ContentModel>)
    {
        println(weather[0].weather)
        println(weather[0].weather[0])
        val adapter = ResultsRecyclerViewAdapter(this, weather)
        binding.searchResults.adapter = adapter
        binding.searchResults.layoutManager = LinearLayoutManager(this)
        binding.searchResults.setHasFixedSize(true)
    }
}
