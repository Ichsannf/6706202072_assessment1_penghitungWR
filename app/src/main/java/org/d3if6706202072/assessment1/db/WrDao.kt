package org.d3if6706202072.assessment1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WrDao {
    @Insert
    fun insert(wr: WrEntity)
    @Query("SELECT * FROM wr ORDER BY id DESC")
    fun getLastBmi(): LiveData<List<WrEntity>>
    @Query("DELETE FROM wr")
    fun clearData()
}
