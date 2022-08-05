package com.example.searchgourmetapp.di

import android.content.Context
import androidx.room.Room
import com.example.searchgourmetapp.database.FavoriteShopDatabase
import com.example.searchgourmetapp.database.FavoriteShopDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): FavoriteShopDatabase{
        return Room.databaseBuilder(
            appContext,
            FavoriteShopDatabase::class.java,
            "favorite_shop_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShopDao(database: FavoriteShopDatabase): FavoriteShopDatabaseDao{
        return database.favoriteShopDatabaseDao()
    }
}