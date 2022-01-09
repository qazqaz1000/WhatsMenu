package com.nckim.whatsmenu

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.nckim.whatsmenu.adapter.RestaurantAdapter
import com.nckim.whatsmenu.data.RestaurantData
import kotlinx.android.synthetic.main.activity_raffle.*
import java.util.concurrent.TimeUnit
import com.naver.maps.map.util.FusedLocationSource




//import kotlinx.android.synthetic.main.activity_main.*

class RaffleActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener{
    private lateinit var mapView: MapView
    private lateinit var naverMap : NaverMap

    lateinit var  restaurantAdapter : RestaurantAdapter
    val datas = mutableListOf<RestaurantData>()

    private var mLatitude: Double = 0.0
    private var mLongitude: Double = 0.0

    private var locationSource: FusedLocationSource? = null


    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raffle)

        initRestaurantRecyclerView()

        mapView = findViewById(R.id.mapview)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

    }

    fun updateLatLng(){
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
//        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        var gpsListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude
                Log.d("Test2", "GPS Location changed, Latitude: $latitude" +
                        ", Longitude: $longitude")
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                Log.d("Test","d")
            }
            override fun onProviderEnabled(provider: String) {
                Log.d("Test","f")
            }
            override fun onProviderDisabled(provider: String) {
                Log.d("Test","65")
            }
        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10L, 1000* 60.0f, gpsListener)
        if (checkLocationServicesStatus(locationManager)) {
            val location: Location? =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val ageMs = TimeUnit.NANOSECONDS.toMillis(
                SystemClock.elapsedRealtimeNanos()
                    - (location?.getElapsedRealtimeNanos() ?: 0)
            )
            Log.d("Test", "age : " + ageMs)
            if (location != null) {
                mLatitude = location.latitude
                mLongitude = location.longitude
                Log.d(
                    "Test", "GPS Location changed, Latitude: $mLatitude" +
                            ", Longitude: $mLongitude"
                )
            }
        }
    }


    fun checkLocationServicesStatus(locationManager : LocationManager): Boolean {
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun initRestaurantRecyclerView() {
        restaurantAdapter = RestaurantAdapter(this)
        restaurant_recyclerview.adapter = restaurantAdapter

        datas.apply {
            add(RestaurantData("양식", "파스타"))
            add(RestaurantData("양식", "스테이크"))
            add(RestaurantData("한식", "국밥"))
        }

        restaurantAdapter.datas = datas
        restaurantAdapter.notifyDataSetChanged()
    }

    override fun onMapReady(p0: NaverMap) {
        this.naverMap = p0

        updateLatLng()
//        val cameraPosition = CameraPosition(LatLng(mLatitude, mLongitude), 16.0)
//        naverMap.cameraPosition=cameraPosition
        locationSource = FusedLocationSource(this, 1001)
        naverMap.locationSource = locationSource
        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);
    }

    override fun onLocationChanged(p0: Location) {
        Log.d(
            "Test2", "GPS Location changed, Latitude: ${p0.latitude}" +
                    ", Longitude: ${p0.longitude}"
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(locationSource?.onRequestPermissionsResult(requestCode, permissions, grantResults) == true) {
            if(!locationSource!!.isActivated()) {
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
                return;
            } else {
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

}