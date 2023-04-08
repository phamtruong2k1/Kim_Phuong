package com.kimphuong.manage.di.module

import androidx.lifecycle.ViewModelProvider
import com.kimphuong.manage.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Furkan on 2019-10-16
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

//    @IntoMap
//    @Binds
//    @ViewModelKey(SplashViewModel::class)
//    abstract fun provideSplashViewModel(splashViewModel: SplashViewModel): ViewModel
//
//    @IntoMap
//    @Binds
//    @ViewModelKey(MainViewModel::class)
//    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel


}
