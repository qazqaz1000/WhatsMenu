package com.nckim.data.model.kakao

import com.google.gson.annotations.SerializedName

// 장소명, 주소, 좌표만 받는 클래스
data class KakaoResponse(
    var documents: List<Place>          // 검색 결과
)

data class Place(
    var place_name: String,             // 장소명, 업체명
    var category_group_name: String,
    var category_name : String,
    var address_name: String,           // 전체 지번 주소
    var road_address_name: String,      // 전체 도로명 주소
    var x: String,                      // X 좌표값 혹은 longitude
    var y: String,                      // Y 좌표값 혹은 latitude

)