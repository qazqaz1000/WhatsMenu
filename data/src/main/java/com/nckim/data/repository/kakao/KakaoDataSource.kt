package com.nckim.data.repository.kakao

import com.nckim.data.model.kakao.KakaoResponse
import io.reactivex.Single

interface KakaoDataSource {
    fun getSearchKakaoPlace(key: String,
                            query: String,
                            x : String,
                            y : String,
                            radius : Int) : Single<KakaoResponse>
}