package code.challenge.mvvmsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import code.challenge.mvvmsample.R
import code.challenge.mvvmsample.model.ContentModel

class ResultsRecyclerViewAdapter(_context : Context, _List:Array<ContentModel>) : RecyclerView.Adapter<ResultsRecyclerViewAdapter.WeatherViewHolder>() {

    val context = _context
    val list = _List

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.forecast_Item_layout,parent,false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val characterContent = list[position]
        holder.weatherView.text = characterContent.weather[0].main
        holder.weatcherDescription.text = "Temp: ${characterContent.main.temp}"

    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val weatherView : TextView = itemView.findViewById(R.id.weatherView)
        val weatcherDescription : TextView = itemView.findViewById(R.id.temp)
    }

}