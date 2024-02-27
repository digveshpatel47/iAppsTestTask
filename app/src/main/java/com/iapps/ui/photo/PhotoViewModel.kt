package com.iapps.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iapps.data.remote.base.Result
import com.iapps.data.remote.photo.response.PhotoItemResponse
import com.iapps.domain.photo.PhotoUseCase
import com.iapps.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class PhotoViewModel(private val photoUseCase: PhotoUseCase) : BaseViewModel(), KoinComponent {
     private val _photoItems = MutableLiveData<List<PhotoItemResponse>>()
     val photoItems: LiveData<List<PhotoItemResponse>> = _photoItems

    fun fetchPhotoItems() {
        viewModelScope.launch {
            photoUseCase.fetchPhotoItems().onStart {
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
                            _photoItems.value = it.items
                            //userInsertUseCase.insertUser(it)
                        }
                    }
                }
            }
        }
    }

    fun fetchSortedPhotoItems() {
        /* viewModelScope.launch {
             val items = repository.getPhotoItemsSortedByPublished()
             _photoItems.value = items
         }*/
    }
}