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
import com.nckim.whatsmenu.views.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class KakaoFragment : Fragment() {

    private val viewModel: KakaoViewModel by viewModels()
    private lateinit var binding: FragmentKakaoBinding
    private lateinit var ct: Context

    private var kakaoMap : MapView? = null

    private var curLat = ""
    private var curLon = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kakao, container, false)
        binding.lifecycleOwner = this
        ct = container!!.context
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
            getCurrentLocation()
            viewModel.requestKakaoPlace(curLat, curLon)      //현재 좌표 (경도 위도)로 검색
        }
    }

    private fun initViewModelCallBack(){
        with(viewModel){
            places.observe(viewLifecycleOwner) {
                var count = 0
                places.value?.forEach {
                    println("${count++}. ${it.place_name}")
                    addMarker(it.place_name, it.y.toDouble(), it.x.toDouble())
                }
            }
        }
    }

    private fun getCurrentLocation(){
        val act = activity as HomeActivity
        if(act != null){
            act.getCurrentLocation { location ->
                var latitude = 0.0
                var longitude = 0.0
                if(location != null){
                    latitude = location.latitude
                    longitude = location.longitude
                    curLat = latitude.toString()
                    curLon = longitude.toString()
                    Log.d("CheckCurrentLocation", "현재 내 위치 값: ${latitude}, ${longitude}")
                    Toast.makeText(ct, "현재 내 위치 값: ${latitude}, ${longitude}", Toast.LENGTH_SHORT).show()

                    updateKakaoCurrentPosition(latitude, longitude)

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
        }
    }

    fun updateKakaoCurrentPosition(latitude : Double, longitude : Double){
        // 중심점 변경
        kakaoMap?.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

    }

    fun addMarker(name: String, latitude: Double, longitude: Double) {
        kakaoMap?.addPOIItem(createMarker(name, latitude, longitude))
    }

    fun createMarker(name: String, latitude: Double, longitude: Double) : MapPOIItem {
        val mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        val marker = MapPOIItem()
        marker.itemName = name
        marker.tag = 0
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        return marker
    }
}