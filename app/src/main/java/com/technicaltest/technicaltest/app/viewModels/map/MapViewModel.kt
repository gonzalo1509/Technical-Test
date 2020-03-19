package com.technicaltest.technicaltest.app.viewModels.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technicaltest.technicaltest.R
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.bussiness.entities.mobilitieResources.MobilitieResourceResponseEntitie
import com.technicaltest.technicaltest.bussiness.useCases.mobilitieResources.MobilitieResourcesUseCase
import com.technicaltest.technicaltest.utilities.app.ApplicationResourcesUtilities
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MapViewModel : ViewModel() {

    init {
        TechnicalTestApplication.technicalTestApplication.appComponent.inject(this)
    }

    private val TAG: String = MapViewModel::class.java.simpleName

    @Inject
    lateinit var disposables: CompositeDisposable

    @Inject
    lateinit var mobilitieResourcesUseCase: MobilitieResourcesUseCase

    @Inject
    lateinit var applicationResourcesUtilities: ApplicationResourcesUtilities

    // MutableLiveData
    private val getMobilitieResourcesLiveData: MutableLiveData<Response<List<MobilitieResourceResponseEntitie>>> =
        MutableLiveData()

    private val getMobilitieResourcesErrorLiveData: MutableLiveData<String> =
        MutableLiveData()

    override fun onCleared() {
        if (!disposables.isDisposed) disposables.dispose()
        super.onCleared()
        Log.d(TAG, "On cleared called")
    }

    fun getMobilitieResourcesLiveData(): MutableLiveData<Response<List<MobilitieResourceResponseEntitie>>> {
        return getMobilitieResourcesLiveData
    }

    fun getMobilitieResourcesErrorLiveData(): MutableLiveData<String> {
        return getMobilitieResourcesErrorLiveData
    }

    fun doGetMobilitieResources(
        location: String,
        lowerLeftLatLon: String,
        upperRightLatLon: String
    ) {
        Log.v(TAG, "init doGetMobilitieResources")

        getMobilitieResources(location, lowerLeftLatLon, upperRightLatLon)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getMobilitieResources(
        location: String,
        lowerLeftLatLon: String,
        upperRightLatLon: String
    ) {
        Log.v(TAG, "init getMobilitieResources")

        disposables.add(
            mobilitieResourcesUseCase.getMobilitiesResources(
                location,
                lowerLeftLatLon,
                upperRightLatLon
            )
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> getMobilitieResourcesLiveData.postValue(response as? Response<List<MobilitieResourceResponseEntitie>>) },
                { error ->
                    Log.e(
                        TAG, applicationResourcesUtilities.getResourceById(
                            R.string.txt_error_when_get_mobilitie_resources
                        ), error
                    )
                    getMobilitieResourcesErrorLiveData.postValue(
                        applicationResourcesUtilities.getResourceById(
                            R.string.txt_error_when_get_mobilitie_resources
                        )
                    )
                }
            )
        )
    }
}