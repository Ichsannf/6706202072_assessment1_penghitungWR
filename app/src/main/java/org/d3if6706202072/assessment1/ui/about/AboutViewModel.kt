package org.d3if6706202072.assessment1.ui.about

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if6706202072.assessment1.model.TentangAplikasi
import org.d3if6706202072.assessment1.network.ApiStatus
import org.d3if6706202072.assessment1.network.WRApi


class AboutViewModel : ViewModel() {

    private val tentangAplikasi = MutableLiveData<TentangAplikasi>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)

            try {
                tentangAplikasi.postValue(WRApi.service.getWR())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("AboutViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getTentangAplikasiWR(): LiveData<TentangAplikasi> = tentangAplikasi

    fun getStatus(): LiveData<ApiStatus> = status
}