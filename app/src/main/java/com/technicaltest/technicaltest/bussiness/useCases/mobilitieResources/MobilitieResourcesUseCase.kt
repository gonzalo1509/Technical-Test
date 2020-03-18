package com.technicaltest.technicaltest.bussiness.useCases.mobilitieResources

import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import com.technicaltest.technicaltest.dataSources.meep.dataSources.mobilitieResources.MobilitieResourcesDataSource
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MobilitieResourcesUseCase @Inject constructor(private val mobilitieResourcesDataSource: MobilitieResourcesDataSource){

    fun getMobilitiesResources(location: String, lowerLeftLatLon: String, upperRightLatLon: String):  Single<Response<List<MobilitieResourceResponseEntitie?>>> {
        return mobilitieResourcesDataSource
            .getMobilitiesResources(location, lowerLeftLatLon, upperRightLatLon)
    }
}