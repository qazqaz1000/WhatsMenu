package com.nckim.whatsmenu.views.kakao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nckim.data.api.ApiClient
import com.nckim.domain.model.kakao.KakaoPlace
import com.nckim.domain.usecase.GetKakaoPlaceUseCase
import com.nckim.whatsmenu.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class KakaoViewModel @Inject constructor(
     private val kakaoPlaceUseCase: GetKakaoPlaceUseCase
): BaseViewModel() {

    private val _places = MutableLiveData<MutableList<KakaoPlace>>()
    val places : LiveData<MutableList<KakaoPlace>> get(){
        return _places
    }

    var query = MutableLiveData<String>()
    init {
        query.value = "Hihihivvv"
    }

    fun requestKakaoPlace(
        x: String,
        y: String){
        compositeDisposable.add(
            kakaoPlaceUseCase.invoke(ApiClient.KAKAO_API_KEY,"맛집","127.072793", "37.567312", 500)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ places ->
                    if(places.isEmpty()){
                        Log.e("NCTEST", "######## fail")
                        //fail
                    }else{
                        _places.value = places as ArrayList<KakaoPlace>

                        Log.e("NCTEST", "######## succes")
                    }
                }, {
                    //fail

                    Log.e("NCTEST", "######## fail " + it.message)
                })
        )

    }
}