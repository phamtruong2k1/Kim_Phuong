package com.kimphuong.manage.di.module


import com.kimphuong.manage.MainActivity
import com.kimphuong.manage.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun mainActivity(): MainActivity

//    @PerActivity
//    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
//    internal abstract fun splashActivity(): SplashActivity

}
