package io.choizzy.pay.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.choizzy.pay.AppController
import io.choizzy.pay_lib.di.ChoizzyPayDepsModule
import io.choizzy.pay_lib.di.ChoizzyPayModule
import javax.inject.Singleton

@Component(
    modules = [
        ChoizzyPayModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)

interface ApplicationComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    /*  
     * This is our custom Application class
     * */
    fun inject(appController: AppController)
}