package com.iapps.ui.photo

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.iapps.databinding.ActivityPhotoBinding
import com.iapps.ui.base.BaseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension


@OptIn(KoinApiExtension::class)
class PhotosActivity : BaseActivity() {
    private val photoAdapter: PhotoAdapter by lazy { PhotoAdapter() }
   // private val viewModel: PhotoViewModel by viewModels()
    private val viewModel by viewModel<PhotoViewModel>()
    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initViewModel(viewModel)
        initObserve()
        init()

    }

    private fun init() {
        binding.rvPhoto.adapter = photoAdapter
        viewModel.fetchPhotoItems()
        viewModel.fetchSortedPhotoItems()
    }

    private fun initObserve() {
        lifecycleScope.launch {
            viewModel.photoItems.collectLatest { response ->
                photoAdapter.submitList(response)
            }
        }
    }
}