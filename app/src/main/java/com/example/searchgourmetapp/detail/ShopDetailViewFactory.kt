package com.example.searchgourmetapp.detail

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.searchgourmetapp.ShopState
import java.lang.IllegalArgumentException

//class ShopDetailViewFactory(private val shopState: ShopState, private val application: Application): ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(ShopDetailViewModel::class.java)){
//            return ShopDetailViewModel(shopState, application) as T
//        }
//        throw IllegalArgumentException("unknown viewModel class")
//    }
//}