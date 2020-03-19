package com.technicaltest.technicaltest.app.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.technicaltest.technicaltest.R
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.app.customViews.map.CustomMarkerInfoWindowView
import com.technicaltest.technicaltest.app.viewModels.map.MapViewModel
import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import com.technicaltest.technicaltest.bussiness.enums.CompanyZoneIdTypes
import com.technicaltest.technicaltest.utilities.app.ApplicationResourcesUtilities
import com.technicaltest.technicaltest.utilities.gdpr.PermissionUtilities
import com.technicaltest.technicaltest.utilities.helpers.CustomAlertDialog
import kotlinx.android.synthetic.main.activity_map_layout.*
import retrofit2.Response
import javax.inject.Inject

class MapActivity : AppCompatActivity(), OnMapReadyCallback,
    OnCameraMoveStartedListener {

    init {
        TechnicalTestApplication.technicalTestApplication.appComponent.inject(this)
    }

    val TAG: String = MapActivity::class.java.simpleName

    @Inject
    lateinit var mapViewModel: MapViewModel

    @Inject
    lateinit var customAlertDialog: CustomAlertDialog

    @Inject
    lateinit var applicationResourcesUtilities: ApplicationResourcesUtilities

    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    private val markersArray = ArrayList<Marker>()

    private var mobilitieResourceDataList: List<MobilitieResourceResponseEntitie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "init onCreate")
        setContentView(R.layout.activity_map_layout)

        PermissionUtilities.requestPermissionsHandler(this)

        customAlertDialog.initLoading(this)

        mapFragment = map as SupportMapFragment
        configMap()

        initializeViewModel()
    }

    override fun onResume() {
        Log.v(TAG, "init onResume")

        customAlertDialog.initLoading(this)
        mapViewModel.doGetMobilitieResources("lisboa", "38.711046,-9.160096", "38.739429,-9.137115")

        super.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        Log.v(TAG, "init onMapReady")

        if (googleMap != null) {
            this.googleMap = googleMap

            googleMap.setOnCameraMoveStartedListener(this)

            if(PermissionUtilities.checkIfPermissionsAreGranted(this)){
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
            }

            googleMap.uiSettings.isCompassEnabled = true
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            googleMap.animateCamera(CameraUpdateFactory.zoomOut());
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(3F));

            mapViewModel.doGetMobilitieResources(
                "lisboa",
                "38.711046,-9.160096",
                "38.739429,-9.137115"
            )
        }
    }

    override fun onCameraMoveStarted(reason: Int) {
        Log.v(TAG, "init onCameraMoveStarted")

        if (reason == OnCameraMoveStartedListener.REASON_GESTURE) {
            Log.d(TAG, "User moves the camera")
            updateMarkers(mobilitieResourceDataList)
        } else if (reason == OnCameraMoveStartedListener.REASON_API_ANIMATION) {
            Log.d(TAG, "User tapped something on the map")
        } else if (reason == OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION) {
            Log.d(TAG, "App Moved the camera")
        }
    }

    private fun configMap() {
        Log.v(TAG, "init configMap")

        mapFragment.getMapAsync(this)

        mapFragment.getMapAsync { googleMap ->
            val customMarkerInfoWindowView = CustomMarkerInfoWindowView()
            googleMap.setInfoWindowAdapter(customMarkerInfoWindowView)
        }
    }

    private fun initializeViewModel() {
        Log.v(TAG, "init initializeViewModel")

        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)

        mapViewModel.getMobilitieResourcesLiveData().observe(
            this,
            Observer<Response<List<MobilitieResourceResponseEntitie>>> { mobilitieResourceDataResponse ->
                processMobilitieResourcesData(
                    mobilitieResourceDataResponse
                )
            })

        mapViewModel.getMobilitieResourcesErrorLiveData().observe(
            this,
            Observer<String> { mobilitieResourceErrorDataResponse ->
                showErrorMessage(
                    mobilitieResourceErrorDataResponse
                )
            })
    }

    private fun processMobilitieResourcesData(mobilitieResourceResponse: Response<List<MobilitieResourceResponseEntitie>>) {
        Log.v(TAG, "init processMobilitieResourceData")

        if (!mobilitieResourceResponse.isSuccessful) {
            Log.e(TAG, "response code is not successful: ${mobilitieResourceResponse.code()}")

            showErrorMessage(applicationResourcesUtilities.getResourceById(R.string.txt_error_when_get_mobilitie_resources))
            return
        }

        mobilitieResourceDataList = mobilitieResourceResponse.body()
        prepareMarkers(mobilitieResourceDataList)

        customAlertDialog.dismissLoading()
    }

    private fun showErrorMessage(message: String) {
        Log.v(TAG, "showErrorMessage")

        customAlertDialog.dismissLoading()
        customAlertDialog.initErrorAlert(this, message)
    }

    private fun prepareMarkers(mobilitieResourceResponseEntitieList: List<MobilitieResourceResponseEntitie>?) {
        Log.v(TAG, "init prepareMarkers")

        googleMap.clear()

        mobilitieResourceResponseEntitieList?.forEach { mobilitieResourceResponseEntitie ->
            addMarker(mobilitieResourceResponseEntitie)
        }
    }

    private fun updateMarkers(mobilitieResourceResponseEntitieList: List<MobilitieResourceResponseEntitie>?) {
        Log.v(TAG, "init updateMarkers")

        val markerIterator: MutableIterator<Marker> = markersArray.iterator()
        val mobilitieResourceResponseEntitieListIterator: Iterator<MobilitieResourceResponseEntitie>? =
            mobilitieResourceResponseEntitieList?.iterator()

        if (mobilitieResourceResponseEntitieListIterator != null) {
            while (markerIterator.hasNext() && mobilitieResourceResponseEntitieListIterator.hasNext()) {
                val currentMarker: Marker = markerIterator.next()
                val currentMobilitieResourceResponseEntitie: MobilitieResourceResponseEntitie =
                    mobilitieResourceResponseEntitieListIterator.next()

                val position = LatLng(
                    currentMobilitieResourceResponseEntitie.lat,
                    currentMobilitieResourceResponseEntitie.lon
                )
                Log.v(
                    TAG,
                    "Go to update marker in following position, lat: ${currentMobilitieResourceResponseEntitie.lat}," +
                            " lon: ${currentMobilitieResourceResponseEntitie.lon}"
                )
                currentMarker.position = position
            }
        }
    }

    private fun addMarker(mobilitieResourceResponseEntitie: MobilitieResourceResponseEntitie) {
        Log.v(TAG, "init addMarker")

        val position =
            LatLng(mobilitieResourceResponseEntitie.lat, mobilitieResourceResponseEntitie.lon)
        Log.v(
            TAG,
            "Go to insert marker in following position, lat: ${mobilitieResourceResponseEntitie.lat}," +
                    " lon: ${mobilitieResourceResponseEntitie.lon}"
        )

        val markerOptions = MarkerOptions()
        markerOptions.position(position)
        markerOptions.title(mobilitieResourceResponseEntitie.name)
        markerOptions.snippet(CompanyZoneIdTypes.lookup(mobilitieResourceResponseEntitie.companyZoneId)?.name)
        markerOptions.icon(getMarkerColor(mobilitieResourceResponseEntitie.companyZoneId))

        Log.v(
            TAG, "Marker details, name: ${mobilitieResourceResponseEntitie.name}, " +
                    "snippet: ${CompanyZoneIdTypes.lookup(mobilitieResourceResponseEntitie.companyZoneId)?.name}"
        )

        val marker: Marker = googleMap.addMarker(markerOptions)
        marker.tag = mobilitieResourceResponseEntitie

        markersArray.add(marker)
    }

    private fun getMarkerColor(companyZoneId: Int): BitmapDescriptor? {
        Log.v(TAG, "init getMarkerColor")
        when (CompanyZoneIdTypes.lookup(companyZoneId)) {
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