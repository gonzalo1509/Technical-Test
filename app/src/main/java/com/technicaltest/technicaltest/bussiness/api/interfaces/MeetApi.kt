package com.technicaltest.technicaltest.bussiness.api.interfaces

import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeetApi {

    @GET("/tripplan/api/v1/routers/{location}/resources")
    fun getMobilitiesResources(@Path("location") location: String,
                               @Query("lowerLeftLatLon") lowerLeftLatLon: String,
                               @Query("upperRightLatLon") upperRightLatLon: String)
                                : Single<List<MobilitieResourceResponseEntitie?>>
}