package com.yash2108.openissuesreader.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.models.ResultUI
import com.yash2108.openissuesreader.repositories.HomeRepository
import com.yash2108.openissuesreader.ui.di.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class HomeViewModel @Inject constructor(): ViewModel() {

    var test = "testing"

    @Inject
    lateinit var repository: HomeRepository

    private val homeDataMutableLiveData = MutableLiveData<ResultUI<List<HomeDataObject>>>()
    val homeDataObjectDataLiveData: LiveData<ResultUI<List<HomeDataObject>>> get() = homeDataMutableLiveData

    fun getIssuesList() = viewModelScope.launch(Dispatchers.IO) {
        repository.getIssuesList()
            .collect { issue ->
                homeDataMutableLiveData.postValue(issue)
            }
    }
}