package com.nckim.whatsmenu.views.kakao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nckim.domain.usecase.GetKakaoPlaceUseCase
import com.nckim.whatsmenu.views.base.BaseViewModel

//    private val kakaoPlaceUseCase: GetKakaoPlaceUseCase
class KakaoViewModel : BaseViewModel(){

    var query = MutableLiveData<String>()
    init {
        query.value = "Hihihivvv"
    }
    fun requestKakaoPlace(){
//        kakaoPlaceUseCase.invoke()
    }
}