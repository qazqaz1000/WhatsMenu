package com.nckim.domain.model.naver

import com.google.gson.annotations.SerializedName

data class Naver (
    var total: Int,
    var start: Int,
    var display: Int,
    var items: List<NaverPlace>

)

data class NaverPlace (
    var title: String,
    var link: String,
    var category: String,
    var address: String,
    var mapx: Int,
    var mapy: Int
)
