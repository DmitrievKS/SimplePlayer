package com.kirdmt.simpleplayer.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirdmt.simpleplayer.R
import com.kirdmt.simpleplayer.data.TrackSearchResult
import com.kirdmt.simpleplayer.data.TrackSearchResults
import com.kirdmt.simpleplayer.databinding.ActivityMainBinding
import com.kirdmt.simpleplayer.ui.adapter.TracksAdapter
import com.kirdmt.simpleplayer.ui.player.PlayerActivity

class MainActivity : AppCompatActivity(), TracksAdapter.OnItemClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = MainViewModel()
        bindData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //var timer = Timer()

            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                    if (it.length > 4)
                        getTracks(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                /*   timer.cancel()
                   val sleep = when(newText.length) {
                       1 -> 1000L
                       2,3 -> 700L
                       4,5 -> 500L
                       else -> 300L
                   }
                   timer = Timer()
                   timer.schedule(sleep) {
                       if (newText.isNullOrEmpty()) {
                           // search

                           Log.d("ResultTag", "There are WOID ")
                       }
                   }*/
                return false
            }

        })

        return true
    }


    private fun bindData() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        binding.executePendingBindings()
        binding.tracksRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun getTracks(sentence: String) {

        viewModel.getTracksList(sentence).observe(this, androidx.lifecycle.Observer { resource ->
            binding.tracksRecycler.adapter = TracksAdapter(this, resource, this)
            //Log.d("ResultTag", "There are ${resource.toString()} ")
        })
    }


    override fun onItemClick(position: Int, trackSearchResult: TrackSearchResult) {

        val playerActivityIntent: Intent = Intent(this, PlayerActivity::class.java).setFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
        )
        playerActivityIntent.putExtra("trackSearchResult", trackSearchResult)
        startActivity(playerActivityIntent)
    }

}
