package com.nckim.whatsmenu.views.kakao

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nckim.whatsmenu.R
import com.nckim.whatsmenu.databinding.FragmentKakaoBinding
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapView
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class KakaoFragment : Fragment() {

    private val viewModel: KakaoViewModel by viewModels()
    private lateinit var binding: FragmentKakaoBinding
    private lateinit var ct: Context

    private var kakaoMap : MapView? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    val PERMISSIONS_REQUEST_CODE = 100


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kakao, container, false)
        binding.lifecycleOwner = this
        ct = container!!.context
        initView()
        initViewModelCallBack()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(ct)

        return binding.root
    }

    private fun initView(){
        if(kakaoMap == null){
            kakaoMap = MapView(activity)
            binding.kakaomapview.addView(kakaoMap)
        }


        binding.testButton.setOnClickListener {
            viewModel.requestKakaoPlace("127.072793", "37.567312")      //현재 좌표 (경도 위도)로 검색
            getCurrentLocation()


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


    private fun getCurrentLocation(){

        var hasFineLocationPermission = ContextCompat.checkSelfPermission(ct,
            Manifest.permission.ACCESS_FINE_LOCATION)
        var hasCoarseLocationPermission = ContextCompat.checkSelfPermission(ct,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? -> // Got last known location. In some rare situations this can be null.

                var latitude = 0.0
                var longitude = 0.0
                if(location != null){
                    latitude = location.latitude
                    longitude = location.longitude
                    Log.d("CheckCurrentLocation", "현재 내 위치 값: ${latitude}, ${longitude}")
                    Toast.makeText(ct, "현재 내 위치 값: ${latitude}, ${longitude}", Toast.LENGTH_SHORT).show()

                    var mGeoCoder =  Geocoder(ct, Locale.KOREAN)
                    var mResultList: List<Address>? = null
                    try{
                        mResultList = mGeoCoder.getFromLocation(
                            latitude!!, longitude!!, 1
                        )
                    }catch(e: IOException){
                        e.printStackTrace()
                    }
                    if(mResultList != null){
                        Log.d("CheckCurrentLocation", mResultList[0].getAddressLine(0))
                        Toast.makeText(ct, mResultList[0].getAddressLine(0), Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), REQUIRED_PERMISSIONS[0])){
                Toast.makeText(ct, "앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
            }else{
                ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
            }
        }
    }

}