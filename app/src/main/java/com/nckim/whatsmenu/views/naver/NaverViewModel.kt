package com.nckim.whatsmenu.views.naver

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nckim.data.api.ApiClient
import com.nckim.domain.model.kakao.KakaoPlace
import com.nckim.domain.model.naver.NaverPlace
import com.nckim.domain.usecase.GetNaverPlaceUseCase
import com.nckim.whatsmenu.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class NaverViewModel @Inject constructor(
    private val naverPlaceUseCase: GetNaverPlaceUseCase
): BaseViewModel(){
    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    private val _places = MutableLiveData<MutableList<NaverPlace>>()
    val places : LiveData<MutableList<NaverPlace>>
        get(){
        return _places
    }


    fun requestNaverPlace(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            _places.postValue( naverPlaceUseCase.invoke(query) as ArrayList<NaverPlace>)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}