package com.nckim.whatsmenu.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nckim.whatsmenu.R
import com.nckim.whatsmenu.databinding.ActivityHomeBinding
import com.nckim.whatsmenu.views.base.BaseActivity
import com.nckim.whatsmenu.views.both.BothFragment
import com.nckim.whatsmenu.views.kakao.KakaoFragment
import com.nckim.whatsmenu.views.naver.NaverFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val kakaoFragment by lazy { KakaoFragment() }
    private val naverFragment by lazy { NaverFragment() }
    private val bothFragment by lazy { BothFragment() }

    private val fragments = listOf<Fragment>(kakaoFragment, naverFragment, bothFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}