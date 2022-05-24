package org.d3if6706202072.assessment1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if6706202072.assessment1.model.HasilWr

class MainViewModel : ViewModel() {
    private val hasilWr = MutableLiveData<HasilWr?>()
    fun hitungWR(tMatch: Float, tWr: Float, wrReq: Float) {
        val tWin = tMatch * (tWr / 100)
        val tLose = tMatch - tWin
        val sisaWr = 100 - wrReq
        val wrResult = 100 / sisaWr
        val seratusPersen = tLose * wrResult
        val final = (seratusPersen - tMatch)
        hasilWr.value = HasilWr(final)
    }

    fun getHasilWr(): LiveData<HasilWr?> = hasilWr
}