package com.yash2108.openissuesreader.di.components

import com.yash2108.openissuesreader.di.modules.HomeViewModelModule
import com.yash2108.openissuesreader.ui.activities.MainActivity
import com.yash2108.openissuesreader.viewmodels.HomeViewModel
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(HomeViewModelModule::class))
interface HomeScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeScreenComponent
    }

    fun inject(viewModel: HomeViewModel)
    fun inject(activity: MainActivity)
}