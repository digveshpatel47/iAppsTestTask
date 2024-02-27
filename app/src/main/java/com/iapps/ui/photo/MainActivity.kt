package com.iapps.ui.photo

import android.os.Bundle
import android.widget.Toast
import com.iapps.databinding.ActivityMainBinding
import com.iapps.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@OptIn(KoinApiExtension::class)
class MainActivity : BaseActivity() {
    private val viewModel by viewModel<PhotoViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initViewModel(viewModel)
        initListener()
        initObserve()
    }
    private fun initListener() {
        viewModel.fetchPhotoItems()
    }

    private fun initObserve() {
        viewModel.photoItems.observe(this) {
            Toast.makeText(this, it.size, Toast.LENGTH_SHORT).show()
        }
    }
}