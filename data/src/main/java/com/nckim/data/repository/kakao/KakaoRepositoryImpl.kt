package com.nckim.data.repository.kakao

import com.nckim.data.model.kakao.KakaoResponse
import com.nckim.domain.model.kakao.Place
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
        return kakaoDataSource.getSearchKakaoPlace(key, query, x, y, radius).flatMap {
            Single.just(it.documents.toList().map {
                Place(
                    it.place_name,
                    it.category_group_name,
                    it.category_name,
                    it.address_name,
                    it.road_address_name,
                    it.x,
                    it.y
                )

            })
        }
    }
}