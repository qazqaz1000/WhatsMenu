package com.nckim.whatsmenu

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PointF
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.nckim.whatsmenu.adapter.RestaurantAdapter
import com.nckim.whatsmenu.data.RestaurantData
import kotlinx.android.synthetic.main.activity_raffle.*
import com.naver.maps.map.util.FusedLocationSource
import java.io.*
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import android.os.StrictMode
import android.view.View
import com.nckim.whatsmenu.searchplace.ResultSearchKeyword
import com.nckim.whatsmenu.searchplace.SearchKeyword
import com.nckim.whatsmenu.searchplace.SearchKeyword.SearchKeywordCallback
import net.daum.mf.map.api.MapView as KakaoMap
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi

import androidx.recyclerview.widget.LinearSmoothScroller

import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint


//import kotlinx.android.synthetic.main.activity_main.*

class RaffleActivity : AppCompatActivity(), OnMapReadyCallback, NaverMap.OnMapClickListener{

    private lateinit var naverView: MapView
    private lateinit var naverMap : NaverMap

    lateinit var  restaurantAdapter : RestaurantAdapter
    val datas = mutableListOf<RestaurantData>()

    private var locationSource: FusedLocationSource? = null


    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private lateinit var kakaoMap : KakaoMap
    private var mLatitude: Double = 0.0
    private var mLongitude: Double = 0.0

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raffle)
        today_menu_textbox.setOnClickListener(View.OnClickListener {
//            restaurant_recyclerview.smoothScrollToPosition(50)
            onSearchKeyword()

        })
        initRestaurantRecyclerView()

        naverView = findViewById(R.id.naverview)
        naverView.onCreate(savedInstanceState)
        naverView.getMapAsync(this)

        kakaoMap = KakaoMap(this)
        kakaoview.addView(kakaoMap)
    }

    private fun onSearchKeyword(){
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location: Location? = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val longitude: Double = location!!.getLongitude()
        val latitude: Double = location!!.getLatitude()
        Log.e("Test", "lati : $latitude, longi : $longitude")
        SearchKeyword.searchKeyword("맛집", longitude.toBigDecimal().toPlainString(), latitude.toBigDecimal().toPlainString(), 500,
            object : SearchKeywordCallback {
                override fun resultCallback(result: ResultSearchKeyword?) {
                    restaurantAdapter = RestaurantAdapter(applicationContext)
                    restaurant_recyclerview.adapter = restaurantAdapter
                    restaurant_recyclerview.layoutManager = SpeedyLinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                    restaurant_recyclerview.layoutParams.height = 50
                    datas.clear()
                    datas.apply {
                        if(!result?.documents.isNullOrEmpty()){
                            for(document in result!!.documents){
                                val ret = document.category_name.trim().split(">")

                                add(RestaurantData(ret.last(), document.place_name))
                            }
                        }
                    }

                    restaurantAdapter.datas = datas
                    restaurantAdapter.notifyDataSetChanged()
                    restaurant_recyclerview.smoothScrollToPosition(50)
                    updateKakaoCurrentPosition(latitude, longitude)

                }
            })
    }

    fun updateKakaoCurrentPosition(latitude : Double, longitude : Double){
        // 중심점 변경
        kakaoMap.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

        kakaoMap.addPOIItem(createMarker(latitude, longitude))
        kakaoMap.addPOIItem(createMarker(latitude+0.001, longitude+0.001))
    }

    fun createMarker(latitude: Double, longitude: Double) : MapPOIItem{
        val mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        val marker = MapPOIItem()
        marker.itemName = "Default Marker"
        marker.tag = 0
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        return marker
    }

    fun checkLocationServicesStatus(locationManager : LocationManager): Boolean {
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    override fun onStart() {
        super.onStart()
        naverView.onStart()
    }

    override fun onResume() {
        super.onResume()
        naverView.onResume()
    }

    override fun onPause() {
        super.onPause()
        naverView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        naverView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        naverView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        naverView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        naverView.onLowMemory()
    }

    private fun initRestaurantRecyclerView() {
        onSearchKeyword()
    }

    override fun onMapReady(p0: NaverMap) {
        this.naverMap = p0

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = locationSource
        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);


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
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        getRestaurant()
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun getRestaurant(){
        val clientId = "ZdiaeLQbLVaYAQODPAUH" //애플리케이션 클라이언트 아이디값"

        val clientSecret = "vY8uGx7Sqm" //애플리케이션 클라이언트 시크릿값"


        var text: String? = null
        text = try {
            URLEncoder.encode("맛집", "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("검색어 인코딩 실패", e)
        }

        val apiURL =
            "https://openapi.naver.com/v1/search/local.json?query=$text&display=10" // json 결과

        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
        val requestHeaders: MutableMap<String, String> = HashMap()
        requestHeaders["X-Naver-Client-Id"] = clientId
        requestHeaders["X-Naver-Client-Secret"] = clientSecret
        val responseBody = get(apiURL, requestHeaders)
        Log.d("Test", "result : " + responseBody);
    }

    private operator fun get(apiUrl: String, requestHeaders: Map<String, String>): String? {
        val con: HttpURLConnection = connect(apiUrl)
        return try {
            con.setRequestMethod("GET")
            for ((key, value) in requestHeaders) {
                con.setRequestProperty(key, value)
            }
            val responseCode: Int = con.getResponseCode()
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                readBody(con.getInputStream())
            } else { // 에러 발생
                readBody(con.getErrorStream())
            }
        } catch (e: IOException) {
            throw RuntimeException("API 요청과 응답 실패", e)
        } finally {
            con.disconnect()
        }
    }

    private fun connect(apiUrl: String): HttpURLConnection {
        return try {
            val url = URL(apiUrl)
            url.openConnection() as HttpURLConnection
        } catch (e: MalformedURLException) {
            throw RuntimeException("API URL이 잘못되었습니다. : $apiUrl", e)
        } catch (e: IOException) {
            throw RuntimeException("연결이 실패했습니다. : $apiUrl", e)
        }
    }

    private fun readBody(body: InputStream): String? {
        val streamReader = InputStreamReader(body)
        try {
            BufferedReader(streamReader).use { lineReader ->
                val responseBody = StringBuilder()
                var line: String?
                while (lineReader.readLine().also { line = it } != null) {
                    responseBody.append(line)
                }
                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는데 실패했습니다.", e)
        }
    }

    override fun onMapClick(p0: PointF, p1: LatLng) {
//        getRestaurant()
    }

    class SpeedyLinearLayoutManager : LinearLayoutManager {
        constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
            context,
            orientation,
            reverseLayout
        )

        override fun smoothScrollToPosition(
            recyclerView: RecyclerView,
            state: RecyclerView.State,
            position: Int
        ) {
            val linearSmoothScroller: LinearSmoothScroller =
                object : LinearSmoothScroller(recyclerView.context) {

                    override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
//                        Log.e("test", "computeScrollVectorForPosition" + targetPosition + " target : " + this.targetPosition + " position : " + position + " curpos : " + getPosition(getChildAt(0)!!))
                        MILLISECONDS_PER_INCH += 1000 * (getPosition(getChildAt(0)!!) / targetPosition) * 100f
                        return super.computeScrollVectorForPosition(targetPosition)
                    }

                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
//                        Log.e("test", "calculateSpeedPerPixel : " + MILLISECONDS_PER_INCH)
                        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
                    }
                }
            linearSmoothScroller.targetPosition = position
            startSmoothScroll(linearSmoothScroller)
        }

        companion object {
            private var MILLISECONDS_PER_INCH : Float = 100f //default is 25f (bigger = slower)
        }
    }
}