package org.d3if6706202072.assessment1.model

import com.squareup.moshi.Json

data class TentangAplikasi(
    @Json(name = "tentang_aplikasi")
    val tentangAplikasi: String
)
