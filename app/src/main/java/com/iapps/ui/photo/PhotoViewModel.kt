package com.iapps.ui.photo

import androidx.lifecycle.viewModelScope
import com.iapps.data.base.Result
import com.iapps.data.photo.PhotoRepository
import com.iapps.data.photo.local.PhotoEntity
import com.iapps.data.photo.remote.response.PhotoModel
import com.iapps.exts.photoEntityListToPhotoModelList
import com.iapps.exts.photoModelListToPhotoEntityList
import com.iapps.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PhotoViewModel(private val repository: PhotoRepository) : BaseViewModel() {

    private val _photoItems = MutableSharedFlow<List<PhotoModel>>(1)
    val photoItems = _photoItems.asSharedFlow()

    fun fetchPhotoItems() {
        viewModelScope.launch {
            repository.fetchPhotoItems().onStart {
                _progress.value = true
            }.catch {
                _errorMessage.value = it.message
                _progress.value = false
            }.collect { result ->
                when (result) {
                    is Result.Error -> {
                        _errorMessage.value = result.message
                        _progress.value = false
                    }

                    is Result.Loading -> {
                        _progress.value = true
                    }

                    is Result.Success -> {
                        _progress.value = false
                        result.data?.let {
                            insertPhotoIntoLocal(it.items.photoModelListToPhotoEntityList())
                        }
                    }
                }
            }
        }
    }

    fun fetchSortedPhotoItems() {
        viewModelScope.launch {
            repository.getPhotoItemsSortedByPublished().flowOn(Dispatchers.IO)
                .catch {
                    _errorMessage.value = it.message
                    _progress.value = false
                } .collectLatest {
                    _photoItems.emit(it.photoEntityListToPhotoModelList())
                }
        }
    }

    private fun insertPhotoIntoLocal(photoItems: List<PhotoEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAll(photoItems)
        }
    }
}