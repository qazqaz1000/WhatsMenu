package com.nckim.data.repository.kakao

import com.nckim.data.api.KakaoApiInterface
import com.nckim.data.model.kakao.KakaoResponse
import io.reactivex.Single

class KakaoDataSourceImpl (
    private val kakaoApiInterface: KakaoApiInterface
        ) : KakaoDataSource{
    override fun getSearchKakaoPlace(key: String,
                                     query: String,
                                     x : String,
                                     y : String,
                                     radius : Int): Single<KakaoResponse> {
        kakaoApiInterface.getSearchKakaoPlace(key, query, x, y, radius)
    }
}