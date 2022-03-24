package com.nckim.data.model.naver

import com.google.gson.annotations.SerializedName

data class NaverResponse (
    @SerializedName("total")
    var total: Int,
    @SerializedName("start")
    var start: Int,
    @SerializedName("display")
    var display: Int,
    @SerializedName("items")
    var items: List<NaverResponsePlace>

)

data class NaverResponsePlace
    (
    @SerializedName("title")
    var title: String,
    @SerializedName("link")
    var link: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("loadAddress")
    var loadAddress: String,
    @SerializedName("mapx")
    var mapx: Int,
    @SerializedName("mapy")
    var mapy: Int
)