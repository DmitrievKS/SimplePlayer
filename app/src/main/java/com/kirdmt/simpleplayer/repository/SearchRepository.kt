package com.kirdmt.simpleplayer.repository

import androidx.lifecycle.MutableLiveData
import com.kirdmt.simpleplayer.data.TrackSearchResults
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepository() {

    fun searchTracks(sentence: String): MutableLiveData<TrackSearchResults> {

        val TrackSearchLiveData: MutableLiveData<TrackSearchResults> = MutableLiveData()

        val retrofitInstance = ApiService.create()
        retrofitInstance.search(query = "$sentence")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                TrackSearchLiveData.value = result

            }, { error ->
                //kotlin.error.printStackTrace()
            })

        return TrackSearchLiveData
    }

}