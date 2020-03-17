package com.technicaltest.technicaltest.app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.orange.agentsdk.Utilities.ApplicationUtilities.Log.CustomLog
import com.technicaltest.technicaltest.R
import com.technicaltest.technicaltest.app.viewModels.map.MapViewModel
import okhttp3.ResponseBody
import retrofit2.Response

class MapActivity: AppCompatActivity() {

    val TAG: String = MapActivity::class.java.simpleName

    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map_layout)

        init()
    }

    private fun init(){
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        mapViewModel.getMobilitieResources().observe(this, Observer { mobilitieResourceDataResponse ->
            processMobilitieResourcesData(mobilitieResourceDataResponse) })

        mapViewModel.doGetMobilitieResources("lisboa","38.711046,-9.160096","38.739429,-9.137115")
    }

    private fun processMobilitieResourcesData(mobilitieResourceDataResponse: Response<ResponseBody>){
        CustomLog.d(TAG, "Valorrr: ${mobilitieResourceDataResponse.body()}")
    }
}