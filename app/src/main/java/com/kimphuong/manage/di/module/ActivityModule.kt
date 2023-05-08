package com.kimphuong.manage.di.module


import com.kimphuong.manage.di.scope.PerActivity
import com.kimphuong.manage.ui.account.AddAccountActivity
import com.kimphuong.manage.ui.main.MainActivity
import com.kimphuong.manage.ui.sign.SignAccountActivity
import com.kimphuong.manage.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun mainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun splashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun addAccountActivity(): AddAccountActivity

}
