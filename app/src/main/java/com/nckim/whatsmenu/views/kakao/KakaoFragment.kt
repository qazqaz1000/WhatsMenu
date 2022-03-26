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
import kotlinx.android.synthetic.main.activity_raffle.*
import net.daum.mf.map.api.MapView

class KakaoFragment : Fragment() {

    private val viewModel: KakaoViewModel by viewModels()
    private lateinit var binding: FragmentKakaoBinding

    private lateinit var kakaoMap : MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kakao, container, false)
        binding.lifecycleOwner = this
        initView()
        return binding.root
    }

    private fun initView(){
        kakaoMap = MapView(activity)
        binding.kakaomapview.addView(kakaoMap)
    }
}