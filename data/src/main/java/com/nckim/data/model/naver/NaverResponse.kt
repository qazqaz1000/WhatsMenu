package com.nckim.data.model.naver

import com.google.gson.annotations.SerializedName

data class NaverResponse (
    @SerializedName("title")
    val title: String
)