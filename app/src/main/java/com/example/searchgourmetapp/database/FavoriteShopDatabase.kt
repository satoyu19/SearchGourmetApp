package com.example.searchgourmetapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteShop::class], version = 1, exportSchema = false)
abstract class FavoriteShopDatabase: RoomDatabase() {

//    abstract val favoriteShopDatabaseDao: FavoriteShopDatabaseDao
    abstract fun favoriteShopDatabaseDao(): FavoriteShopDatabaseDao
    //シングルトン
//    companion object{
//        @Volatile
//        private var INSTANCE: FavoriteShopDatabase? = null
//
//        fun getInstance(context: Context): FavoriteShopDatabase {
//
//            synchronized(this){
//                var instance = INSTANCE
//                if(instance == null){
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        FavoriteShopDatabase::class.java,
//                        "favorite_shop_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }

}