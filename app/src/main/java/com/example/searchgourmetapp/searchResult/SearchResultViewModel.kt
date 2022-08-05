package com.example.searchgourmetapp.searchResult

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgourmetapp.ShopState
import com.example.searchgourmetapp.network.GourmetApiService
import com.example.searchgourmetapp.network.Shop
import kotlinx.coroutines.launch
import java.lang.Exception

//現在地、範囲
class SearchResultViewModel(private val lat: Float, private val lng: Float, private val range: String, private val keyword: String): ViewModel() {

    //検索結果
    private var _searchResponse = MutableLiveData<List<ShopState>>()
    val searchResponse: LiveData<List<ShopState>>
        get() = _searchResponse

    init {
        getGourmetProperties()
    }

    //検索結果の取得
    private fun getGourmetProperties() {
        viewModelScope.launch {
            try {
                val result = GourmetApiService.GourmetApi.retrofitService.getNearPlace(lat, lng, range, keyword)
                val searchShop: MutableList<Shop> = result.results.shop as MutableList<Shop>

                var  shops = mutableListOf<ShopState>()

                if(searchShop.isNotEmpty()){
                    for(shop in searchShop){
                        val shopState = ShopState(
                            shop.id,
                            shop.name,
                            shop.access,
                            shop.address,
                            shop.catch,
                            shop.budget.name,
                            shop.logoImg,
                            shop.open,
                            shop.stationName,
                            shop.genre.name,
                            shop.photo.pc.l,
                            shop.photo.mobile.s
                        )
                        shops.add(shopState)
                    }
                }
                Log.i("SearchGourmetApp", shops.toString())
                _searchResponse.value = shops

            }catch (e: Exception){
                Log.i("SearchGourmetApp", e.message.toString())
            }
        }
    }

}