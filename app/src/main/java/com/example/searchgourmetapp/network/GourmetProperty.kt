package com.example.searchgourmetapp.network

import com.squareup.moshi.Json

// TODO:nullで帰る可能性あるのか？確認
//利用するデータ
data class GourmetProperty(
    val results: Result,
)

data class Result(
    val shop: List<Shop>
)

data class Shop(
    val id: String,
    val name: String,          // 店舗名称
    val access: String, // 交通アクセス
    val budget: Budget,        // 予算
    val genre: Genre,          // ジャンル
    @Json(name="logo_image") val logoImg: String,
    val address: String,       // 住所
    val open: String,          // 営業時間
    val catch: String,         //キャッチフレーズ
    val photo: Photo,
    @Json(name="station_name")  val stationName: String  // 最寄り駅
)

data class Photo(
    val mobile: Mobile,                 // モバイル端末向けの写真
    val pc: PC
)
data class PC(
    val l: String,       //サイズ
    val m: String,
    val s: String
)

data class Mobile(
   val l: String,       //サイズ
   val s: String
)

data class Budget(
    val name: String,          // 予算
)

data class Genre(
    val name: String,          // ジャンル
)
