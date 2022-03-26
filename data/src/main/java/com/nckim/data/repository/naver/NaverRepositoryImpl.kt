package com.nckim.data.repository.naver

import com.nckim.domain.model.naver.Naver
import com.nckim.domain.model.naver.NaverPlace
import com.nckim.domain.repository.NaverRepository
import io.reactivex.Single

class NaverRepositoryImpl constructor(
    private val naverDataSource: NaverDataSource
): NaverRepository {
    override fun getNaverPlace(query: String): Single<List<NaverPlace>> {
        return naverDataSource.getSearchNaverPlace(query).flatMap {
            Single.just(it.items.toList().map {
                NaverPlace(
                    it.title,
                    it.link,
                    it.category,
                    it.address,
                    it.mapx,
                    it.mapy
                )
            })
        }
    }

}