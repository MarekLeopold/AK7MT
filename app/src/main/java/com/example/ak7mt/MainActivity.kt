package com.example.ak7mt

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ak7mt.fragments.HodinyFragment
import com.example.ak7mt.fragments.PocasiFragment
import com.example.ak7mt.fragments.PoznamkyFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_poznamky.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hodinyFragment = HodinyFragment()
        val pocasiFragment = PocasiFragment()
        val nastaveniFragment = PoznamkyFragment()

        makeCurrentFragment(pocasiFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.pocasi -> makeCurrentFragment(pocasiFragment)
                R.id.Nastaveni -> makeCurrentFragment(nastaveniFragment)
                R.id.Hodiny -> makeCurrentFragment(hodinyFragment)
            }
            true
        }
    }


    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}