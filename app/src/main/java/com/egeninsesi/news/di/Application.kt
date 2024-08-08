package com.egeninsesi.news.di

import dagger.hilt.android.HiltAndroidApp

import android.app.Application
import com.google.firebase.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("test")

        }
    }

}