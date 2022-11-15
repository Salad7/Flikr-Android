package com.example.flickr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
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
class FlikrFragment(activity :MainActivity) : Fragment() {
    lateinit var binding : FragmentFlickrBinding
    lateinit var viewModel :FlickerFragmentViewModel
    val request_code = 0
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
            executeSearch("")

        }

        setHasOptionsMenu(true)
        return binding.root
    }

     fun executeSearch(query :String){
         binding.apply {
        lifecycleScope.launch {
            if(query.equals("")){
                viewModel.fetchData()
            }
            else{
                viewModel.fetchByQuery(query)
            }
            viewModel.immutableFlickrStateFlow.collect {
                var adapter =
                    FlickrAdapter(it.photos.photo, this@FlikrFragment.requireContext())
                rv.layoutManager = GridLayoutManager(this@FlikrFragment.requireContext(), 3)
                rv.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
         }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_flickr_list, menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == 0){
            if(data!!.hasExtra("msg")){
                var message = data.getStringExtra("msg")
                Log.d("FlikrFragment","Msg: "+message)
                executeSearch(message!!)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.search_item){
//            Toast.makeText(requireContext(),"Clicked Search!",Toast.LENGTH_LONG).show()
            var alertDialog = FlickrDialogFragment()
            alertDialog.setTargetFragment(this@FlikrFragment,request_code)
            alertDialog.show(requireFragmentManager(),"TAG")


        }
        return super.onOptionsItemSelected(item)
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