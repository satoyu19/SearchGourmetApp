package com.example.searchgourmetapp.search


import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.searchgourmetapp.database.FavoriteShop
import com.example.searchgourmetapp.database.FavoriteShopDatabase
import com.example.searchgourmetapp.database.FavoriteShopDatabaseDao
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//class SearchViewModel(application: Application) : AndroidViewModel(application) {
@HiltViewModel
class SearchViewModel @Inject constructor(private val database: FavoriteShopDatabaseDao) : ViewModel() {

    var lat = 0F
    var lng = 0F

    //位置情報が許可状態か判別するフラグ
    var isLocation = false

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //データベース
//    private val database: FavoriteShopDatabaseDao by lazy {
//        return@lazy FavoriteShopDatabase.getInstance(application).favoriteShopDatabaseDao
//    }

    //お気に入り店舗リスト
    private var _shops = MutableLiveData<MutableList<FavoriteShop>?>()
    val shops: LiveData<MutableList<FavoriteShop>?>
        get() = _shops

    //    お気に入りの全店舗を取得
    fun getFavoriteShops() {
        viewModelScope.launch {
            _shops.value = getAllFavoriteShop()
        }
    }

    private suspend fun getAllFavoriteShop(): MutableList<FavoriteShop>? {
        var shops = database.getAllShop()
        //空でなければshopを、からならnullを返す
        return shops.ifEmpty {
            null
        }
    }

    //位置情報権限の登録
    fun registrationLocation(activity: Activity){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
//checkSelfPermission→アプリがパーミッションを持っていることを確認、ユーザーがすでに特定の権限をアプリに付与しているかどうかを確認
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // ACCESS_FINE_LOCATIONの許可を求める標準ダイアログを表示する(許可がされていない)
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(activity, permissions, 1000)
        }

        fusedLocationClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                lat = it.latitude.toFloat()
                lng = it.longitude.toFloat()
            }
        }
    }

    //位置情報権限の確認
    fun checkLocation(activity: Activity){
        isLocation = !(ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
    }
}