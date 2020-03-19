package com.technicaltest.technicaltest.dataSources.meep.dataSources.mobilitieResources

import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import com.technicaltest.technicaltest.dataSources.meep.Interfaces.MeetApi
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MobilitieResourcesDataSource @Inject constructor(private val meetApi: MeetApi) {

    fun getMobilitiesResources(
        location: String,
        lowerLeftLatLon: String,
        upperRightLatLon: String
    ): Single<Response<List<MobilitieResourceResponseEntitie>>> {
        return meetApi.getMobilitiesResources(location, lowerLeftLatLon, upperRightLatLon)
    }
}