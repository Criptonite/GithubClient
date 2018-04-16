package com.gubadev.injectproject.dagger

import android.app.Activity
import com.gubadev.injectproject.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}