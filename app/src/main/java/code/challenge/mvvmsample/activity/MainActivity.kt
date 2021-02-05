package code.challenge.mvvmsample.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import code.challenge.mvvmsample.R
import code.challenge.mvvmsample.databinding.ActivityMainBinding
import code.challenge.mvvmsample.adapters.AnimeRecyclerViewAdapter
import code.challenge.mvvmsample.model.AnimeContent
import code.challenge.mvvmsample.utils.NetworkAvailability

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set up the provider for the view model
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        if(NetworkAvailability().isNetworkConnected(this))
        {
            mainActivityViewModel.getAnimeFromAPIAndStore()
        }
        else
        {
            Toast.makeText(this,"No internet found. Showing cached list in the view",Toast.LENGTH_LONG).show()
        }

        // Observes the anime list data for the recycler view
        mainActivityViewModel.getAllAnimeList().observe(this, { countryList ->
            setUpCountryRecyclerView(countryList!!)
        })
    }

    // Method for quick set up of recycler view
    private fun setUpCountryRecyclerView(anime : List<AnimeContent>)
    {
        val countryRecyclerViewAdapter = AnimeRecyclerViewAdapter(this, anime)
        binding.animeRecyclerView.adapter = countryRecyclerViewAdapter
        binding.animeRecyclerView.layoutManager = GridLayoutManager(this,2)
        binding.animeRecyclerView.setHasFixedSize(true)
    }

    // The two override methods below are for inflating the toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.search){
            // Starts another activity for the searching of the charaters page
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}
