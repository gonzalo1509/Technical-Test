package com.technicaltest.technicaltest.app.customViews.map

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.technicaltest.technicaltest.R
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication.Companion.technicalTestApplication
import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import javax.inject.Inject

class CustomMarkerInfoWindowView : GoogleMap.InfoWindowAdapter {

    init {
        technicalTestApplication.appComponent.inject(this)
    }

    private lateinit var markerItemView: View

    @Inject
    lateinit var layoutInflater: LayoutInflater

    private var TAG: String = CustomMarkerInfoWindowView::class.java.simpleName

    @SuppressLint("InflateParams")
    override fun getInfoWindow(marker: Marker?): View {
        Log.v(TAG, "init getInfoWindow")

        markerItemView = layoutInflater.inflate(R.layout.marker_info_window, null)

        val mobilitieResourceResponseEntitie: MobilitieResourceResponseEntitie = marker?.tag
                as MobilitieResourceResponseEntitie

        val markerId: TextView =
            markerItemView.findViewById(R.id.markerId)
        val markerName: TextView =
            markerItemView.findViewById(R.id.markerName)
        val markerX: TextView =
            markerItemView.findViewById(R.id.markerX)
        val markerY: TextView =
            markerItemView.findViewById(R.id.markerY)
        val markerScheduledArrival: TextView =
            markerItemView.findViewById(R.id.markerScheduledArrival)
        val markerLocationType: TextView =
            markerItemView.findViewById(R.id.markerLocationType)
        val markerCompanyZoneId: TextView =
            markerItemView.findViewById(R.id.markerCompanyZoneId)
        val markerLat: TextView =
            markerItemView.findViewById(R.id.markerLat)
        val markerLon: TextView =
            markerItemView.findViewById(R.id.markerLon)

        markerId.text = mobilitieResourceResponseEntitie.id
        markerName.text = mobilitieResourceResponseEntitie.name
        markerX.text = mobilitieResourceResponseEntitie.x.toString()
        markerY.text = mobilitieResourceResponseEntitie.y.toString()
        markerScheduledArrival.text = mobilitieResourceResponseEntitie.scheduledArrival.toString()
        markerLocationType.text = mobilitieResourceResponseEntitie.locationType.toString()
        markerCompanyZoneId.text = mobilitieResourceResponseEntitie.companyZoneId.toString()
        markerLat.text = mobilitieResourceResponseEntitie.lat.toString()
        markerLon.text = mobilitieResourceResponseEntitie.lon.toString()

        return markerItemView
    }

    override fun getInfoContents(marker: Marker?): View? {
        Log.v(TAG, "init getInfoContents")

        return null
    }
}