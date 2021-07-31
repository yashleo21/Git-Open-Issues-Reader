package com.yash2108.openissuesreader.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yash2108.openissuesreader.adapters.CommentsAdapter
import com.yash2108.openissuesreader.databinding.FragmentDetailBinding
import com.yash2108.openissuesreader.models.DetailDataObject
import com.yash2108.openissuesreader.models.ResultUI
import com.yash2108.openissuesreader.ui.di.scopes.FragmentScoped
import com.yash2108.openissuesreader.viewmodels.HomeViewModel

@FragmentScoped
class DetailFragment: Fragment(), CommentsAdapter.Callback {

    private val TAG = DetailFragment::class.java.simpleName

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<HomeViewModel>()

    lateinit var adapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initAdapter()
        initObservers()
        fetchData()
    }

    private fun initUI() {
        viewModel.currentIssue?.run {
            binding.tvTitle.text = title
            binding.tvDesc.text = body

            val formattedDate = viewModel.input.parse(updated_at)
            binding.tvDate.text = viewModel.output.format(formattedDate)

            Glide.with(binding.ivAvatar)
                .load(user?.avatar_url)
                .into(binding.ivAvatar)

            binding.tvUserName.text = user?.login
        }

    }

    private fun initAdapter() {
        adapter = CommentsAdapter(this)
        binding.rvComments.adapter = adapter
    }

    private fun initObservers() {
        viewModel.detailDataLiveData.observe(viewLifecycleOwner, Observer {
            onDataStateLoaded(it)
        })
    }

    private fun onDataStateLoaded(state: ResultUI<List<DetailDataObject>>) {
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

    private fun updateAdapter(data: List<DetailDataObject>) {
        adapter.submitList(data.toList())
    }

    private fun fetchData() {
        viewModel.currentIssue?.comments_url?.let {
            if (it.isNotBlank()) {
                viewModel.getComments(it)
            }
        }
    }

    override fun onItemClicked(data: DetailDataObject, position: Int, transitionView: View) {
        Log.d(TAG, "Item clicked")
    }

    override fun onDestroyView() {
        viewModel.detailDataLiveData.removeObservers(this)
        viewModel.detailDataMutableLiveData = MutableLiveData()
        super.onDestroyView()
    }
}