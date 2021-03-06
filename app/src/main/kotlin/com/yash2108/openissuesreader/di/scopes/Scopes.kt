package com.yash2108.openissuesreader.ui.di.scopes

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelScoped

@Qualifier
annotation class ApplicationContext

@Qualifier
annotation class ActivityContext