package com.nckim.data.repository.kakao

import com.nckim.data.api.KakaoApiInterface
import com.nckim.data.model.kakao.KakaoResponse
import io.reactivex.Single
import javax.inject.Inject

class KakaoDataSourceImpl @Inject constructor(
    private val kakaoApiInterface: KakaoApiInterface
        ) : KakaoDataSource{
    override fun getSearchKakaoPlace(key: String,
                                     query: String,
                                     x : String,
                                     y : String,
                                     radius : Int): Single<KakaoResponse> {
        return kakaoApiInterface.getSearchKakaoPlace(key, query, x, y, radius)
    }
}