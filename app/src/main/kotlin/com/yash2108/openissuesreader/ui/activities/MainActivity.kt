package com.yash2108.openissuesreader.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.yash2108.openissuesreader.R
import com.yash2108.openissuesreader.application.MyApplication
import com.yash2108.openissuesreader.databinding.ActivityMainBinding
import com.yash2108.openissuesreader.di.components.HomeScreenComponent
import com.yash2108.openissuesreader.ui.di.scopes.ActivityScoped
import com.yash2108.openissuesreader.ui.fragments.HomeFragment
import com.yash2108.openissuesreader.viewmodels.HomeViewModel

@ActivityScoped
class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val viewmodel: HomeViewModel by viewModels()
    lateinit var homeScreenComponent: HomeScreenComponent

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        homeScreenComponent = (application as MyApplication).appComponent
            .homeScreenComponentBuilder.create(this)
        homeScreenComponent.inject(viewmodel)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inflateFragment()
    }

    private fun inflateFragment() {
        supportFragmentManager.commit {
            add<HomeFragment>(R.id.fragment_containing_view)
        }
    }
}