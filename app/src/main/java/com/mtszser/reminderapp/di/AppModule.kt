package com.mtszser.reminderapp.di

import android.app.Application
import androidx.room.Room
import com.mtszser.reminderapp.model.UserDao
import com.mtszser.reminderapp.model.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): UserDatabase {
        return UserDatabase.getDatabase(application)
    }

    @Singleton
    @Provides
    fun getDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }



}