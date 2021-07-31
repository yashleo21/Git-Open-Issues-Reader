package com.yash2108.openissuesreader.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.yash2108.openissuesreader.R
import com.yash2108.openissuesreader.adapters.HomeAdapter
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.databinding.FragmentHomeBinding
import com.yash2108.openissuesreader.models.ResultUI
import com.yash2108.openissuesreader.ui.activities.MainActivity
import com.yash2108.openissuesreader.ui.di.scopes.FragmentScoped
import com.yash2108.openissuesreader.viewmodels.HomeViewModel
import javax.inject.Inject

@FragmentScoped
class HomeFragment: Fragment(), HomeAdapter.Callback {

    private val TAG = HomeFragment::class.java.simpleName

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<HomeViewModel>()

    lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObservers()
        fetchData()
    }

    private fun initAdapter() {
        adapter = HomeAdapter(this)
        binding.rvItems.adapter = adapter
    }

    private fun initObservers() {
        viewModel.homeDataObjectDataLiveData.observe(viewLifecycleOwner, Observer {
            onDataStateLoaded(it)
        })
    }

    private fun onDataStateLoaded(state: ResultUI<List<HomeDataObject>>) {
        when (state) {
            is ResultUI.Loading -> {
                binding.pb.visibility = View.VISIBLE
                Log.d(TAG, "LOADING TRIGGERED")
            }

            is ResultUI.Error -> {
                binding.pb.visibility = View.GONE
                Log.d(TAG, "Error triggered")
            }

            is ResultUI.Success -> {
                Log.d(TAG, "Success")
                binding.pb.visibility = View.GONE
                updateAdapter(state.data)
            }
        }
    }

    private fun updateAdapter(data: List<HomeDataObject>) {
        Log.d(TAG, "Update adapter called")
        adapter.submitList(data.toList())
    }

    private fun fetchData() {
        viewModel.getIssuesList()
    }

    override fun onItemClicked(data: HomeDataObject, position: Int, transitionView: View) {
        viewModel.currentIssue = data
        activity?.supportFragmentManager?.commit {
            add<DetailFragment>(R.id.fragment_containing_view)
            addToBackStack("detailFragment")
        }
    }
}