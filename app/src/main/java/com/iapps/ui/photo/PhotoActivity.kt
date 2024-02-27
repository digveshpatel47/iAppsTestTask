package com.iapps.ui.photo

import android.os.Bundle
import android.widget.Toast
import com.iapps.databinding.ActivityPhotoBinding
import com.iapps.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@OptIn(KoinApiExtension::class)
class PhotoActivity : BaseActivity() {
    private val viewModel by viewModel<PhotoViewModel>()
    private lateinit var binding: ActivityPhotoBinding
    private lateinit var adapter: PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
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
            adapter = PhotoAdapter(context = this, photoItems = it)
            binding.rvPhoto.adapter = adapter
        }
    }
}