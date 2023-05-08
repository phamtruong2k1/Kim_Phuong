package com.kimphuong.manage.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kimphuong.manage.di.ViewModelFactory
import com.kimphuong.manage.di.key.ViewModelKey
import com.kimphuong.manage.ui.account.AccountViewModel
import com.kimphuong.manage.ui.enterdata.EnterDataViewModel
import com.kimphuong.manage.ui.enterdata.choose.ChooseDataViewModel
import com.kimphuong.manage.ui.main.MainViewModel
import com.kimphuong.manage.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Furkan on 2019-10-16
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(SplashViewModel::class)
    abstract fun provideSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(AccountViewModel::class)
    abstract fun provideAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(EnterDataViewModel::class)
    abstract fun provideEnterDataViewModel(enterDataViewModel: EnterDataViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ChooseDataViewModel::class)
    abstract fun provideChooseDataViewModel(chooseDataViewModel: ChooseDataViewModel): ViewModel


}
