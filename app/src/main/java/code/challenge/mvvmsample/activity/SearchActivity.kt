package code.challenge.mvvmsample.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import code.challenge.mvvmsample.databinding.ActivitySearchBinding
import code.challenge.mvvmsample.utils.NetworkAvailability
import code.challenge.mvvmsample.adapters.ResultsRecyclerViewAdapter
import code.challenge.mvvmsample.model.CharacterContent

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    lateinit var searchActivityViewModel: SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set up the provider for the view model
        searchActivityViewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        binding.submitQuery.setOnClickListener {
            if(NetworkAvailability().isNetworkConnected(this))
            {
                searchActivityViewModel.getCharactersFromAPIAndStore(binding.editSearch.text.toString())
            }
            else
            {
                Toast.makeText(this,"No internet found. Showing cached list in the view",Toast.LENGTH_LONG).show()
            }
        }

        // Observes the character list data for the recycler view
        searchActivityViewModel.getAllCharactersList().observe(this, { characterList ->
            setUpRecyclerView(characterList!!)
        })
    }

    private fun setUpRecyclerView(characters : List<CharacterContent>)
    {
        val characterRecyclerViewAdapter = ResultsRecyclerViewAdapter(this, characters)
        binding.searchResults.adapter = characterRecyclerViewAdapter
        binding.searchResults.layoutManager = GridLayoutManager(this,2)
        binding.searchResults.setHasFixedSize(true)
    }
}
