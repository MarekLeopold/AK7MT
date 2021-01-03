package com.example.ak7mt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ak7mt.fragments.HodinyFragment
import com.example.ak7mt.fragments.PocasiFragment
import com.example.ak7mt.fragments.PoznamkyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hodinyFragment = HodinyFragment()
        val pocasiFragment = PocasiFragment()
        val poznamkyFragment = PoznamkyFragment()

        makeCurrentFragment(pocasiFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.pocasi -> makeCurrentFragment(pocasiFragment)
                R.id.Poznamky -> makeCurrentFragment(poznamkyFragment)
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