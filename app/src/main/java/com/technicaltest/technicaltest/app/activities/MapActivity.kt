package com.technicaltest.technicaltest.app.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.technicaltest.technicaltest.R
import com.technicaltest.technicaltest.app.viewModels.map.MapViewModel
import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie


class MapActivity: AppCompatActivity() {

    val TAG: String = MapActivity::class.java.simpleName

    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_map_layout)

        initializeViewModel()
    }

    private fun initializeViewModel(){
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        mapViewModel.getMobilitieResourcesLiveData().observe(this, Observer<List<MobilitieResourceResponseEntitie>> {
                mobilitieResourceDataResponse -> processMobilitieResourcesData(mobilitieResourceDataResponse) })

        mapViewModel.doGetMobilitieResources("lisboa","38.711046,-9.160096","38.739429,-9.137115")
    }

    private fun processMobilitieResourcesData(mobilitieResourceResponse: List<MobilitieResourceResponseEntitie>) {
        Log.v(TAG, "init processMobilitieResourceData")

        mobilitieResourceResponse.forEach { mobilitieResourceResponseEntitie ->
            Log.v("ID: ", mobilitieResourceResponseEntitie.id)
            Log.v("Name: ", mobilitieResourceResponseEntitie.name)
        }
    }
}