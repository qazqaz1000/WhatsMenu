package com.nckim.data.repository.kakao

import com.nckim.domain.model.kakao.KakaoPlace
import com.nckim.domain.repository.KakaoRepository
import io.reactivex.Single
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(
    private val kakaoDataSource: KakaoDataSource
) : KakaoRepository{
    override fun getKakaoPlace(
        key: String,
        query: String,
        x: String,
        y: String,
        radius: Int
    ): Single<List<KakaoPlace>> {
        return kakaoDataSource.getSearchKakaoPlace(key, query, x, y, radius).flatMap {
            Single.just(it.documents.toList().map {
                KakaoPlace(
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