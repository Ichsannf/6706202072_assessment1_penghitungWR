package org.d3if6706202072.assessment1.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if6706202072.assessment1.db.WrDao
import org.d3if6706202072.assessment1.db.WrEntity

class HistoriViewModel(private val db: WrDao) : ViewModel() {
    val data = db.getLastBmi()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
    fun delete(wrEntity: WrEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO){db.delete(wrEntity)}
    }

}