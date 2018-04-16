package com.gubadev.injectproject

import android.app.Application
import com.gubadev.injectproject.dagger.AppComponent
import com.gubadev.injectproject.dagger.AppModule
import com.gubadev.injectproject.dagger.DaggerAppComponent
import com.gubadev.injectproject.dagger.NetModule

class MainApp : Application() {

    private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("http://api.github.com/"))
                .build();

    }


    fun getAppComponent() : AppComponent{
        return appComponent
    }
}