package com.technicaltest.technicaltest.app.viewModels.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orange.agentsdk.Utilities.ApplicationUtilities.Log.CustomLog
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.bussiness.useCases.mobilitieResources.MobilitieResourcesUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class MapViewModel: ViewModel() {

    init {
        TechnicalTestApplication.technicalTestApplication.appComponent.inject(this)
    }

    private val TAG: String = MapViewModel::class.java.simpleName

    @Inject
    lateinit var disposables: CompositeDisposable

    @Inject
    lateinit var mobilitieResourcesUseCase: MobilitieResourcesUseCase

    // MutableLiveData
    private val getMobilitieResources: MutableLiveData<Response<ResponseBody>>  =
        MutableLiveData()

    override fun onCleared() {
        if (!disposables.isDisposed) disposables.dispose()
        super.onCleared()
        CustomLog.d(TAG, "On cleared called")
    }

    fun getMobilitieResources(): LiveData<Response<ResponseBody>>  {
        return getMobilitieResources
    }

    fun doGetMobilitieResources(location: String, lowerLeftLatLon: String, upperRightLatLon: String) {
        getMobilitieResources(location, lowerLeftLatLon, upperRightLatLon)
    }

    private fun getMobilitieResources(location: String, lowerLeftLatLon: String, upperRightLatLon: String){
        disposables.add(
            mobilitieResourcesUseCase.getMobilitiesResources(location, lowerLeftLatLon, upperRightLatLon)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { getMobilitieResources::postValue },
                { error -> CustomLog.e(TAG, "Ocurrió un error al obtener los recursos de movilidad", error) }
            )
        )
    }
}