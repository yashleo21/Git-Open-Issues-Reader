package com.yash2108.openissuesreader.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash2108.openissuesreader.database.entity.Home
import com.yash2108.openissuesreader.models.ResultUI
import com.yash2108.openissuesreader.repositories.HomeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel: ViewModel() {

    @Inject
    lateinit var repository: HomeRepository

    private val homeDataMutableLiveData = MutableLiveData<ResultUI<ArrayList<Home>>>()
    val homeDataLiveData: LiveData<ResultUI<ArrayList<Home>>> get() = homeDataMutableLiveData

    fun getIssuesList() = viewModelScope.launch {
        repository.getIssuesList()
            .collect { issue ->
                homeDataMutableLiveData.postValue(issue)
            }
    }
}