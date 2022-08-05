package com.example.searchgourmetapp.search

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.searchgourmetapp.ShopState
import com.example.searchgourmetapp.detail.ShopDetailViewModel
import java.lang.IllegalArgumentException

//class SearchShopViewFactory(private val application: Application): ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
//            return SearchViewModel(application) as T
//        }
//        throw IllegalArgumentException("unknown viewModel class")
//    }
//}