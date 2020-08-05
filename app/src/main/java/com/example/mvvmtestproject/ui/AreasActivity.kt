package com.example.mvvmtestproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmtestproject.R
import org.jetbrains.anko.toast

class AreasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areas)

        var cityId = intent.getStringExtra("cityId")


        //list/areas
        toast(cityId!!)
    }
}