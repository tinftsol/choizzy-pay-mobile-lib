package io.choizzy.pay

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.choizzy.pay.di.ApplicationComponent
import io.choizzy.pay.di.DaggerApplicationComponent
import javax.inject.Inject


class AppController : Application(), HasAndroidInjector {

    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingServiceInjector
    }

    private fun initDagger() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
        applicationComponent.inject(this)
    }
}