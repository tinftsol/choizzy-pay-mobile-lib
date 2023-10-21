package io.choizzy.pay_lib.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.choizzy.pay_lib.ChoizzyPayActivity

@Module
abstract class ChoizzyPayModule {

    @ContributesAndroidInjector(modules = [ChoizzyPayDepsModule::class])
    abstract fun injectMainActivity(): ChoizzyPayActivity
}