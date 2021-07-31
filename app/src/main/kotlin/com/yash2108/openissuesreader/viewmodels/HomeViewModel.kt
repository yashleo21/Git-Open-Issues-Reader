package com.yash2108.openissuesreader.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.models.DetailDataObject
import com.yash2108.openissuesreader.models.ResultUI
import com.yash2108.openissuesreader.repositories.HomeRepository
import com.yash2108.openissuesreader.ui.di.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@ActivityScoped
class HomeViewModel @Inject constructor(): ViewModel() {
    @Inject
    lateinit var repository: HomeRepository

    var input: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    var output: SimpleDateFormat = SimpleDateFormat("MM-dd-yyyy")

    private val homeDataMutableLiveData = MutableLiveData<ResultUI<List<HomeDataObject>>>()
    val homeDataObjectDataLiveData: LiveData<ResultUI<List<HomeDataObject>>> get() = homeDataMutableLiveData

    var detailDataMutableLiveData = MutableLiveData<ResultUI<List<DetailDataObject>>>()
    val detailDataLiveData: LiveData<ResultUI<List<DetailDataObject>>> get() = detailDataMutableLiveData

    var currentIssue: HomeDataObject? = null

    fun getIssuesList() = viewModelScope.launch(Dispatchers.IO) {
        repository.getIssuesList()
            .collect { issue ->
                homeDataMutableLiveData.postValue(issue)
            }
    }

    fun getComments(url: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getComments(url)
            .collect { comments ->
                detailDataMutableLiveData.postValue(comments)
            }
    }
}