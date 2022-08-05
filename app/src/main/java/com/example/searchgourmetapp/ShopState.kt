package com.example.searchgourmetapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopState (
    val id: String,     //店舗ID
    val name: String,    //店名
    val access: String, //アクセス情報
    val address: String,  //住所
    val catch: String,     //店舗キャッチフレーズ
    val average: String,   //平均値段
    val logoImg: String,    //ロゴイメージ
    val open: String,          // 営業時間
    val stationName: String,  // 最寄り駅
    val genre: String,          // ジャンル
    val pcL: String,          // 店舗トップ写真（大）URL
    val mobileS: String     //リストロゴ用
) : Parcelable
