package com.nckim.data.repository.kakao

import com.nckim.data.model.kakao.KakaoResponse
import com.nckim.data.model.kakao.Place
import com.nckim.domain.model.kakao.Kakao
import com.nckim.domain.repository.KakaoRepository
import io.reactivex.Single

class KakaoRepositoryImpl constructor(
    private val kakaoDataSource: KakaoDataSource
) : KakaoRepository{
    override fun getKakaoPlace(
        key: String,
        query: String,
        x: String,
        y: String,
        radius: Int
    ): Single<List<Place>> {
        kakaoDataSource.getSearchKakaoPlace(key, query, x, y, radius).flatMap {
            Single.just( it.documents.toList().map {

            } )
        }
    }
}