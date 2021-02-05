package code.challenge.mvvmsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import code.challenge.mvvmsample.R
import code.challenge.mvvmsample.model.AnimeContent
import com.squareup.picasso.Picasso

class AnimeRecyclerViewAdapter(_context : Context, _animeList:List<AnimeContent>) : RecyclerView.Adapter<AnimeRecyclerViewAdapter.CountryViewHolder>() {

    val context = _context
    val countryList = _animeList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.anime_item_layout,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val anime = countryList[position]
        Picasso.get().load(anime.image_url).into(holder.animeImage)
        holder.animeTitle.text = anime.title
        holder.animeType.text = anime.type
        holder.animeRating.text = anime.rated
        holder.animeScore.text = anime.score.toString()

    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val animeTitle : TextView = itemView.findViewById(R.id.char_name)
        val animeType : TextView = itemView.findViewById(R.id.anime_type)
        val animeRating : TextView = itemView.findViewById(R.id.anime_rating)
        val animeImage : ImageView = itemView.findViewById(R.id.char_image)
        val animeScore : TextView = itemView.findViewById(R.id.anime_score)
    }

}