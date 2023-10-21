package io.choizzy.pay.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.choizzy.pay.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}