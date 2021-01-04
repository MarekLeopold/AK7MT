package com.example.ak7mt.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ak7mt.R
import kotlinx.android.synthetic.main.fragment_poznamky.*
import kotlinx.android.synthetic.main.fragment_poznamky.view.*


class PoznamkyFragment : Fragment() {
    val SHARED_PREFS = "sharedPrefs"
    val TEXT = "text"
    var hodnota: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        loadData()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        loadData()
        val view: View = inflater!!.inflate(R.layout.fragment_poznamky, container, false)
        view.textview.text = hodnota
        view.save_button.setOnClickListener { view: View? ->
            saveData()
            textview.text = editText.text.toString()
            editText.getText().clear()
        }

        // Return the fragment view/layout
        return view
    }

    fun saveData() {
        val sharedPreferences = this.activity?.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString(TEXT, editText.text.toString())
        editor?.apply()
    }

    fun loadData() {
        val sharedPreferences = this.activity?.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        hodnota = sharedPreferences?.getString(TEXT, "zlin,cz")

    }

}