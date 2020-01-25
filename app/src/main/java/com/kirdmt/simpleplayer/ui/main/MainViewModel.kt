package com.kirdmt.simpleplayer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirdmt.simpleplayer.data.TrackSearchResults
import com.kirdmt.simpleplayer.repository.SearchRepository

class MainViewModel(
) : ViewModel() {
    private var searchRepository: SearchRepository = SearchRepository()

    fun getTracksList(sentence: String): MutableLiveData<TrackSearchResults> {
        return searchRepository.searchTracks(sentence)
    }

    fun clear() {
        //searchRepository.clear()
    }

}