package org.d3if6706202072.assessment1.ui.hitung

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if6706202072.assessment1.MainActivity
import org.d3if6706202072.assessment1.db.WrDao
import org.d3if6706202072.assessment1.db.WrEntity
import org.d3if6706202072.assessment1.model.HasilWr
import org.d3if6706202072.assessment1.model.hitungWr
import org.d3if6706202072.assessment1.network.UpdateWorker
import java.util.concurrent.TimeUnit

class HitungViewModel(private val db: WrDao) : ViewModel() {
    private val hasilWr = MutableLiveData<HasilWr?>()
    fun hitungWR(tMatch: Float, tWr: Float, wrReq: Float) {
        val dataWr = WrEntity(
            tMatch = tMatch,
            tWr = tWr,
            wrReq = wrReq
        )
        hasilWr.value = dataWr.hitungWr()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataWr)
            }
        }

    }

    fun getHasilWr(): LiveData<HasilWr?> = hasilWr

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}