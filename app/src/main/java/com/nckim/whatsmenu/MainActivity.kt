package com.nckim.whatsmenu

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nckim.whatsmenu.adapter.AreaAdapter
import com.nckim.whatsmenu.data.AreaData
import com.nckim.whatsmenu.decorator.HorizontalItemDecorator
import com.nckim.whatsmenu.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var areaAdapter: AreaAdapter
    val datas = mutableListOf<AreaData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        requestPermission()
    }

    private fun initRecycler(){
        areaAdapter = AreaAdapter(this)
        area_recyclerview.adapter = areaAdapter
        area_recyclerview.addItemDecoration(VerticalItemDecorator(10))
        area_recyclerview.addItemDecoration(HorizontalItemDecorator(20))
        var index : Int = 0
        datas.apply {
            add(AreaData("first" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
            add(AreaData("second" + ++index))
        }

        areaAdapter.datas = datas
        areaAdapter.notifyDataSetChanged()
    }

    private fun requestPermission(){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED ) {
//            showDialog("Permission granted")
            Toast.makeText(this, "grant", Toast.LENGTH_SHORT).show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), // 1
                1001) // 2
        }
    }

}