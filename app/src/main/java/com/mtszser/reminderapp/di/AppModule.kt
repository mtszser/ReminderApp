package com.mtszser.reminderapp.di

import android.app.Application
import androidx.room.Room
import com.mtszser.reminderapp.model.ActivityDao
import com.mtszser.reminderapp.model.DrankWaterDao
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
    fun provideDatabase(application: Application): UserDatabase =
        UserDatabase.getDatabase(application)


    @Singleton
    @Provides
    fun getDao(userDatabase: UserDatabase): UserDao =
        userDatabase.userDao()


    @Singleton
    @Provides
    fun getActivityDao(userDatabase: UserDatabase): ActivityDao =
        userDatabase.activityDao()

    @Singleton
    @Provides
    fun getDrankWaterDao(userDatabase: UserDatabase): DrankWaterDao =
        userDatabase.drankWaterDao()
}
