package com.iapps.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iapps.data.photo.local.PhotoEntity
import com.iapps.data.photo.remote.base.Result
import com.iapps.domain.photo.GetPhotoFromLocalUseCase
import com.iapps.domain.photo.GetPhotoFromRemoteUseCase
import com.iapps.domain.photo.InsertPhotoInLocalUseCase
import com.iapps.exts.toPhotoEntity
import com.iapps.exts.toPhotoModel
import com.iapps.models.PhotoModel
import com.iapps.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class PhotoViewModel(
    private val getPhotoFromRemoteUseCase: GetPhotoFromRemoteUseCase,
    private val getPhotoFromLocalUseCase: GetPhotoFromLocalUseCase,
    private val insertPhotoInLocalUseCase: InsertPhotoInLocalUseCase,
) : BaseViewModel(), KoinComponent {
     private val _photoItems = MutableLiveData<List<PhotoModel>>()
     val photoItems: LiveData<List<PhotoModel>> = _photoItems

    fun fetchPhotoItems() {
        viewModelScope.launch {
            getPhotoFromRemoteUseCase.fetchPhotoItems().onStart {
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
                        result.data?.let { it ->
                             insertPhotoIntoLocal(it.items.map { it.toPhotoEntity() })
                        }
                    }
                }
            }
        }
    }

    fun fetchSortedPhotoItems() {
         viewModelScope.launch {
             getPhotoFromLocalUseCase.getPhotoItemsSortedByPublished().collect{ it ->
                 _photoItems.value = it.map { it.toPhotoModel() }
             }

         }
    }

    private fun insertPhotoIntoLocal(photoItems: List<PhotoEntity>) {
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 insertPhotoInLocalUseCase.insertAll(photoItems)
             }
         }
    }
}