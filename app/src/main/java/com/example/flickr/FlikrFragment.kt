package com.example.flickr

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.Objects.Photo
import com.example.flickr.ViewModels.FlickerFragmentViewModel
import com.example.flickr.databinding.FragmentFlickrBinding

class FlikrFragment : Fragment() {
    lateinit var binding : FragmentFlickrBinding
    lateinit var viewModel :FlickerFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_flickr,container,false)
        binding = FragmentFlickrBinding.bind(v)
        viewModel = FlickerFragmentViewModel()
        return binding.root
    }

    class FlickrViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        lateinit var photo_iv : ImageView
        init {
            photo_iv = itemView.findViewById(R.id.flickr_img)
        }
    }

    class FlickrAdapter(list :ArrayList<Photo>, ctx : Context) : RecyclerView.Adapter<FlickrViewHolder>(){
        var photos = list;
        var context = ctx;
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrViewHolder {
            var v = LayoutInflater.from(context).inflate(R.layout.custom_flikr_item,parent,false)
            return FlickrViewHolder(v)
        }

        override fun onBindViewHolder(holder: FlickrViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return photos.size
        }
    }
}