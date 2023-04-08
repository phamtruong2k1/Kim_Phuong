package com.kimphuong.manage.di.component

import android.app.Application
import com.kimphuong.manage.App
import com.kimphuong.manage.di.module.ActivityModule
import com.kimphuong.manage.di.module.ApplicationModule
import com.kimphuong.manage.di.module.DatabaseModule
import com.kimphuong.manage.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
