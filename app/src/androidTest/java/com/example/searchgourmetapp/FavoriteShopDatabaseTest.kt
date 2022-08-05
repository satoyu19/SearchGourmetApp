//package com.example.searchgourmetapp
//
//import android.content.Context
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.searchgourmetapp.favorite.FavoriteShop
//import com.example.searchgourmetapp.database.FavoriteShopDatabase
//import com.example.searchgourmetapp.favorite.FavoriteShopDatabaseDao
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import java.io.IOException
//
////データべーステスト用
//@RunWith(AndroidJUnit4::class)
//    class FavoriteShopDatabaseTest {
//    private lateinit var favoriteShopDao: FavoriteShopDatabaseDao
//    private lateinit var db: FavoriteShopDatabase
//
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(
//            context, FavoriteShopDatabase::class.java
//        ).build()
//        favoriteShopDao = db.favoriteShopDatabaseDao
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetNight() {
//        val favoriteShop = FavoriteShop()
//        favoriteShopDao.insert(favoriteShop)
//        favoriteShopDao.delete(favoriteShop)
////    }
//    }
//}