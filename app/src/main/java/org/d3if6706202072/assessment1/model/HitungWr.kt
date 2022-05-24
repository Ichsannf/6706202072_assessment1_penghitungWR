package org.d3if6706202072.assessment1.model

import org.d3if6706202072.assessment1.db.WrEntity

fun WrEntity.hitungWr(): HasilWr {
    val tWin = tMatch * (tWr / 100)
    val tLose = tMatch - tWin
    val sisaWr = 100 - wrReq
    val wrResult = 100 / sisaWr
    val seratusPersen = tLose * wrResult
    val final = (seratusPersen - tMatch)

    return HasilWr(final)
}
