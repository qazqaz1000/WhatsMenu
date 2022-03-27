package com.nckim.whatsmenu.views.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nckim.whatsmenu.R
import com.nckim.whatsmenu.databinding.ActivityHomeBinding
import com.nckim.whatsmenu.views.base.BaseActivity
import com.nckim.whatsmenu.views.both.BothFragment
import com.nckim.whatsmenu.views.kakao.KakaoFragment
import com.nckim.whatsmenu.views.naver.NaverFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val kakaoFragment by lazy { KakaoFragment() }
    private val naverFragment by lazy { NaverFragment() }
    private val bothFragment by lazy { BothFragment() }

    private val fragments = listOf<Fragment>(kakaoFragment, naverFragment, bothFragment)

    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val PERMISSIONS_REQUEST_CODE = 100
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        initFragments()
        initBottomNavigation()
    }

    private fun initBottomNavigation(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    changeFragment(kakaoFragment)
                    true
                }
                R.id.page_2 -> {
                    changeFragment(naverFragment)
                    true
                }
                R.id.page_3 -> {
                    changeFragment(bothFragment)
                    true
                }
                else -> false
            }
            true
        }
    }

    private fun initFragments(){
        fragments.forEach {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, it).commit()
        }
        changeFragment(kakaoFragment)
    }

    private fun changeFragment(fragment: Fragment) {
        fragments.forEach {
            if(it == fragment){
                supportFragmentManager.beginTransaction().show(it).commit();
            }else{
                supportFragmentManager.beginTransaction().hide(it).commit();
            }
        }
    }

    public fun getCurrentLocation(onSuccessListener: (Location) -> Unit){
        var hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)
        var hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? -> // Got last known location. In some rare situations this can be null.
                location?.let { onSuccessListener(it) }

            }

        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])){
                Toast.makeText(this, "앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
            }else{
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
            }
        }
    }
}