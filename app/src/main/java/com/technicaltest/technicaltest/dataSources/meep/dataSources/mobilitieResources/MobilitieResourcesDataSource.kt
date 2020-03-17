package com.technicaltest.technicaltest.dataSources.meep.dataSources.mobilitieResources

import com.technicaltest.technicaltest.dataSources.meep.Interfaces.MeetApi
import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import io.reactivex.Single
import javax.inject.Inject

class MobilitieResourcesDataSource @Inject constructor(private val meetApi: MeetApi){

    fun getMobilitiesResources(location:String, lowerLeftLatLon: String, upperRightLatLon: String): Single<List<MobilitieResourceResponseEntitie?>> {
        return meetApi.getMobilitiesResources(location, lowerLeftLatLon, upperRightLatLon)
    }
}