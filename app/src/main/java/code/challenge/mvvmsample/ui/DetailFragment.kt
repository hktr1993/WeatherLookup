package code.challenge.mvvmsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import code.challenge.mvvmsample.databinding.FragmentDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailFragment : BottomSheetDialogFragment() {

    val model : ForecastActivityViewModel by activityViewModels()
    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val position = requireArguments().getInt("pos")

        model.forecastData.observe(viewLifecycleOwner, {
            binding.tempDetail.text = it.list[position].main.temp.toString()
            binding.feelsLike.text = "Feels Like: ${it.list[position].main.feels_like}"
            binding.weather.text = it.list[position].weather[0].main
            binding.weatherDescription.text = it.list[position].weather[0].description
        })

        return view
    }

    companion object {
        fun newInstance(url: Int): DetailFragment {
            val fragmentDemo = DetailFragment()
            val args = Bundle()
            args.putInt("pos", url)
            fragmentDemo.arguments = args
            return fragmentDemo
        }
    }
}