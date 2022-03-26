package com.nckim.whatsmenu.views.naver

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.nckim.whatsmenu.R
import com.nckim.whatsmenu.databinding.FragmentNaverBinding


class NaverFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: NaverViewModel by viewModels()
    private lateinit var binding: FragmentNaverBinding

    private lateinit var naverMap : NaverMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_naver, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_naver, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.naverview.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        binding.naverview.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.naverview.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.naverview.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.naverview.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.naverview.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.naverview.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.naverview.onLowMemory()
    }

    override fun onMapReady(p0: NaverMap) {
        naverMap = p0
    }


}