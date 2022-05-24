package org.d3if6706202072.assessment1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wr")
data class WrEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var tMatch: Float,
    var tWr: Float,
    var wrReq: Float
)