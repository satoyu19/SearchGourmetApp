package com.example.searchgourmetapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "user_favorite_shop_table")
data class FavoriteShop (
    //shopが持っているIDを使用
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "id",      //店舗ID
    @ColumnInfo(name = "name")
    val name: String = "name",    //店名
    @ColumnInfo(name = "access")
    val access: String = "access", //アクセス情報
    @ColumnInfo(name = "address")
    val address: String = "address", //住所
    @ColumnInfo(name = "mobileS")
    val mobileS: String = "mobileS"   //リストロゴ用
)

//DAO
@Dao
interface FavoriteShopDatabaseDao{
    //店舗のお気に入り登録で利用
    @Insert
    suspend fun insert(shop: FavoriteShop)

    //お気に入り店舗削除で利用
    @Delete
    suspend fun delete(shop: FavoriteShop)

    //お気に入り店舗一覧取得で利用
    @Query("SELECT * FROM user_favorite_shop_table")
    suspend fun getAllShop():  MutableList<FavoriteShop>

    //お気に入りリストからidのfavShop登録済みか確認
    @Query("select id from user_favorite_shop_table where id = :id")
    suspend fun checkId(id: String): String?
}