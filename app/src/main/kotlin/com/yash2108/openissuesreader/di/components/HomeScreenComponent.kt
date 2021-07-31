package com.yash2108.openissuesreader.di.components

import android.content.Context
import com.yash2108.openissuesreader.di.modules.HomeScreenModule
import com.yash2108.openissuesreader.ui.activities.MainActivity
import com.yash2108.openissuesreader.ui.di.scopes.ActivityScoped
import com.yash2108.openissuesreader.ui.fragments.HomeFragment
import com.yash2108.openissuesreader.viewmodels.HomeViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = arrayOf(HomeScreenModule::class))
interface HomeScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Context): HomeScreenComponent
    }

    fun inject(viewModel: HomeViewModel)
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
}