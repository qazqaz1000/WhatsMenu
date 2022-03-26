package com.nckim.whatsmenu.views.kakao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.nckim.whatsmenu.R
import com.nckim.whatsmenu.databinding.FragmentKakaoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_raffle.*
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class KakaoFragment : Fragment() {

    private val viewModel: KakaoViewModel by viewModels()
    private lateinit var binding: FragmentKakaoBinding

    private var kakaoMap : MapView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kakao, container, false)
        binding.lifecycleOwner = this
        initView()
        initViewModelCallBack()
        return binding.root
    }

    private fun initView(){
        if(kakaoMap == null){
            kakaoMap = MapView(activity)
            binding.kakaomapview.addView(kakaoMap)
        }


        binding.testButton.setOnClickListener {
            viewModel.requestKakaoPlace("127.072793", "37.567312")      //현재 좌표 (경도 위도)로 검색
        }
    }

    private fun initViewModelCallBack(){
        with(viewModel){
            places.observe(viewLifecycleOwner) {
                var count = 0
                places.value?.forEach {
                    println("${count++}. ${it.place_name}")
                }
            }
        }
    }
}