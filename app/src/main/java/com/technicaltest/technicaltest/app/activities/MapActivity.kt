package com.technicaltest.technicaltest.app.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.technicaltest.technicaltest.R
import com.technicaltest.technicaltest.app.viewModels.map.MapViewModel
import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import com.technicaltest.technicaltest.utilities.enums.CompanyZoneIdTypes
import kotlinx.android.synthetic.main.activity_map_layout.*
import javax.inject.Inject

class MapActivity: AppCompatActivity(), OnMapReadyCallback,
                                        GoogleMap.OnMarkerClickListener{

    val TAG: String = MapActivity::class.java.simpleName

    @Inject
    lateinit var mapViewModel: MapViewModel

    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_map_layout)

        mapFragment = map as SupportMapFragment
        configMap()

        initializeViewModel()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            this.googleMap = googleMap
            mapViewModel.doGetMobilitieResources("lisboa","38.711046,-9.160096","38.739429,-9.137115")
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        //marker.get
        return true
    }

    private fun configMap(){
        mapFragment.getMapAsync(this)
    }

    private fun initializeViewModel(){
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        mapViewModel.getMobilitieResourcesLiveData().observe(this, Observer<List<MobilitieResourceResponseEntitie>> {
                mobilitieResourceDataResponse -> processMobilitieResourcesData(mobilitieResourceDataResponse) })
    }

    private fun processMobilitieResourcesData(mobilitieResourceResponse: List<MobilitieResourceResponseEntitie>) {
        Log.v(TAG, "init processMobilitieResourceData")
        mobilitieResourceResponse.forEach { mobilitieResourceResponseEntitie ->
            addMarker(mobilitieResourceResponseEntitie)
        }
    }

    private fun addMarker(mobilitieResourceResponseEntitie: MobilitieResourceResponseEntitie){
        Log.v(TAG, "init addMarker")

        val position = LatLng(mobilitieResourceResponseEntitie.lat, mobilitieResourceResponseEntitie.lon)
        val markerOptions = MarkerOptions()

        markerOptions.position(position)
        markerOptions.title(mobilitieResourceResponseEntitie.name)
        markerOptions.snippet(CompanyZoneIdTypes.lookup(mobilitieResourceResponseEntitie.companyZoneId)?.name)
        markerOptions.icon(getMarkerColor(mobilitieResourceResponseEntitie.companyZoneId))

        googleMap.addMarker(markerOptions)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position))
    }

    private fun getMarkerColor(companyZoneId: Int): BitmapDescriptor? {
        when(CompanyZoneIdTypes.lookup(companyZoneId)){
            CompanyZoneIdTypes.FIRST_TYPE -> {
                return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            }
            CompanyZoneIdTypes.SECOND_TYPE -> {
                return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
            }
            CompanyZoneIdTypes.THIRD_TYPE -> {
                return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
            }
            CompanyZoneIdTypes.FOURTH_TYPE -> {
                return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)
            }
            CompanyZoneIdTypes.FIFTH_TYPE -> {
                return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)
            }
            CompanyZoneIdTypes.SIXTH_TYPE -> {
                return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            }
            CompanyZoneIdTypes.SEVENTH_TYPE -> {
                return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
            }
        }

        return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
    }
}