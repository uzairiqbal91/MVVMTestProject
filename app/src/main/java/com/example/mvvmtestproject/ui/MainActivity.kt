package com.example.mvvmtestproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtestproject.R
import com.example.mvvmtestproject.adapter.CityAdapter
import com.example.mvvmtestproject.data.model.CityData
import com.example.mvvmtestproject.databinding.ActivityMainBinding
import com.example.mvvmtestproject.ui.event.BaseEvent
import com.example.mvvmtestproject.ui.event.CityNavEvent
import com.example.mvvmtestproject.utils.CustomProgressDialog
import com.example.mvvmtestproject.viewmodel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    @Inject
    lateinit var cityAdapter: CityAdapter

    lateinit var viewModel : CityViewModel
    private val progressDialog = CustomProgressDialog()

    val map: java.util.HashMap<String, String> = java.util.HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        viewModel.navEvent.observe(this, eventObserver)
        binding.adapter = cityAdapter


        cityAdapter.setClickListener(onCityClicked)

        viewModel.getCityData(map)
    }

    private val onCityClicked = object : CityAdapter.OnCityClickListener{
        override fun onCityClicked(data: CityData) {
                startActivity(intentFor<AreasActivity>("cityId" to data.id.toString()))
        }
    }

    private val eventObserver = Observer<BaseEvent<CityNavEvent>> {
        when (val event = it.getEventIfNotHandled()) {
            is CityNavEvent.StartLoading -> {
                progressDialog.show(this@MainActivity, "Please Wait...")
            }
            is CityNavEvent.StopLoading -> {
                progressDialog.dialog.cancel()
            }
            is CityNavEvent.OnCityData -> {
                if(event.list!!.size > 0){
                    cityAdapter.update(event.list)
                }
            }

            is CityNavEvent.Error -> {
                Log.d("TAG", "${event.error}")
                toast(event.error.toString())
            }

            is CityNavEvent.Exception -> {
                Log.d("TAG", "${event.exception?.message.toString()}")
                toast(event.exception?.message.toString())
            }
        }
    }

}