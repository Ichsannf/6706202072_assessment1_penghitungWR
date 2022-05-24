package org.d3if6706202072.assessment1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.d3if6706202072.assessment1.ui.histori.HistoriAdapter

@Dao
interface WrDao {
    @Insert
    fun insert(wr: WrEntity)
    @Query("SELECT * FROM wr ORDER BY id DESC")
    fun getLastBmi(): LiveData<List<WrEntity>>
    @Query("DELETE FROM wr")
    fun clearData()
    @Delete
    fun delete(historyEntity: WrEntity)
}
