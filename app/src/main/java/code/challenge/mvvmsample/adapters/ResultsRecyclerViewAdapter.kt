package code.challenge.mvvmsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import code.challenge.mvvmsample.R
import com.squareup.picasso.Picasso
import code.challenge.mvvmsample.model.CharacterContent

class ResultsRecyclerViewAdapter(_context : Context, _animeList:List<CharacterContent>) : RecyclerView.Adapter<ResultsRecyclerViewAdapter.CountryViewHolder>() {

    val context = _context
    val list = _animeList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.anime_character_item_layout,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val characterContent = list[position]
        Picasso.get().load(characterContent.image_url).into(holder.characterImage)
        holder.characterTitle.text = characterContent.name

    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val characterTitle : TextView = itemView.findViewById(R.id.char_name)
        val characterImage : ImageView = itemView.findViewById(R.id.char_image)
    }

}