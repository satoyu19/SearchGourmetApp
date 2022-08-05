package com.example.searchgourmetapp.searchResult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SearchResultFactory(private val lat: Float, private val lng: Float, private val range: String, private val keyword: String): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchResultViewModel::class.java)){
            return SearchResultViewModel(lat, lng, range, keyword) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}