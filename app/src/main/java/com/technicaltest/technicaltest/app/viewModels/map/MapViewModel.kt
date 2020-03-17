package com.technicaltest.technicaltest.app.viewModels.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import com.technicaltest.technicaltest.bussiness.useCases.mobilitieResources.MobilitieResourcesUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
    private val getMobilitieResourcesLiveData: MutableLiveData<List<MobilitieResourceResponseEntitie>> =
        MutableLiveData()

    override fun onCleared() {
        if (!disposables.isDisposed) disposables.dispose()
        super.onCleared()
        Log.d(TAG, "On cleared called")
    }

    fun getMobilitieResourcesLiveData(): MutableLiveData<List<MobilitieResourceResponseEntitie>>  {
        return getMobilitieResourcesLiveData
    }

    fun doGetMobilitieResources(location: String, lowerLeftLatLon: String, upperRightLatLon: String) {
        getMobilitieResources(location, lowerLeftLatLon, upperRightLatLon)
    }

    private fun getMobilitieResources(location: String, lowerLeftLatLon: String, upperRightLatLon: String){
        disposables.add(
            mobilitieResourcesUseCase.getMobilitiesResources(location, lowerLeftLatLon, upperRightLatLon)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response-> getMobilitieResourcesLiveData.postValue(response as List<MobilitieResourceResponseEntitie>?) },
                { error -> Log.e(TAG, "Ocurrió un error al obtener los recursos de movilidad", error) }
            )
        )
    }
}