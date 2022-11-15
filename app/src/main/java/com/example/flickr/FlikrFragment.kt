package com.example.flickr

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.flickr.Objects.Photo
import com.example.flickr.ViewModels.FlickerFragmentViewModel
import com.example.flickr.databinding.FragmentFlickrBinding
import kotlinx.coroutines.launch

//import com.squareup.picasso.Picasso;
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
        binding.apply {
//            var samples = ArrayList<Photo>()
//            samples.add(Photo("https://w7.pngwing.com/pngs/929/843/png-transparent-lebron-james-jumping-while-holding-basketball-file-formats-lebron-james-image-file-formats-computer-wallpaper-competition-event-thumbnail.png"))
            lifecycleScope.launch {
                viewModel.immutableFlickrStateFlow.collect {

                    var adapter =
                        FlickrAdapter(it.photos.photo, this@FlikrFragment.requireContext())
                    rv.layoutManager = GridLayoutManager(this@FlikrFragment.requireContext(), 3)
                    rv.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

        }
        lifecycleScope.launch {
            viewModel.fetchData()
        }
        return binding.root
    }

    class FlickrViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        lateinit var photo_iv : ImageView
        init {
            photo_iv = itemView.findViewById(R.id.flickr_img)
        }
    }

    class FlickrAdapter(list :List<Photo>, ctx : Context) : RecyclerView.Adapter<FlickrViewHolder>(){
        var photos = list;
        var context = ctx;
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrViewHolder {
            var v = LayoutInflater.from(context).inflate(R.layout.custom_flikr_item,parent,false)
            return FlickrViewHolder(v)
        }

        override fun onBindViewHolder(holder: FlickrViewHolder, position: Int) {
//            Picasso.load(photos.get(position).imgurl).into(holder.photo_iv)
            holder.photo_iv.load(photos.get(position).getImageUrl())
        }

        override fun getItemCount(): Int {
            return photos.size
        }
    }
}