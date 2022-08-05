package com.example.searchgourmetapp.detail

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.searchgourmetapp.ShopState
import com.example.searchgourmetapp.database.FavoriteShop
import com.example.searchgourmetapp.database.FavoriteShopDatabase
import com.example.searchgourmetapp.database.FavoriteShopDatabaseDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

//class ShopDetailViewModel constructor(val shopState: ShopState, application: Application) : AndroidViewModel(application) {
@HiltViewModel
class ShopDetailViewModel @AssistedInject constructor(@Assisted val shopState: ShopState, private val database: FavoriteShopDatabaseDao) : ViewModel() {
    //データベース
//    private val database: FavoriteShopDatabaseDao by lazy {
//        FavoriteShopDatabase.getInstance(application).favoriteShopDatabaseDao
//        }


    //登録確認フラグ
    private var _isId = MutableLiveData<Boolean>()
    val isId: LiveData<Boolean>
    get() = _isId

    fun onMapSearchButtonClick(): Intent {
        // 住所をURLエンコードする
        val searchAddress = URLEncoder.encode(shopState.address, "UTF-8")
        // 地図アプリと連携するURI文字列を生成
        val uriStr = "geo:0:0?q=${searchAddress}"
        val uri = Uri.parse(uriStr)
        return Intent(Intent.ACTION_VIEW, uri)
    }

    //お気に入り店舗を追加
    fun addFavoriteShop() {
        viewModelScope.launch {
            addShop()
        }
    }

    private suspend fun addShop() {
        val newFavoriteShop = FavoriteShop(
            shopState.id,
            shopState.name,
            shopState.access,
            shopState.address,
            shopState.mobileS
        )
        database.insert(newFavoriteShop)
    }

    //idをもとに店舗がお気に入りに登録済みか確認
    fun checkShopId() {
        //データベースのインスタンスを取得
        viewModelScope.launch {
            checkShop()
        }
    }

    private suspend fun checkShop() {
        val result: String? = database.checkId(shopState.id)
        this._isId.value = result != null
    }

    @AssistedFactory
    interface Factory{
        abstract fun create(shopState: ShopState): ShopDetailViewModel
    }
}