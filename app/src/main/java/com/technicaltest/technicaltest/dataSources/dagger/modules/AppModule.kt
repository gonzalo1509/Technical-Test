package com.technicaltest.technicaltest.dataSources.dagger.modules

import android.content.Context
import android.view.LayoutInflater
import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.app.services.retrofit.RetrofitClient
import com.technicaltest.technicaltest.app.viewModels.map.MapViewModel
import com.technicaltest.technicaltest.bussiness.useCases.mobilitieResources.MobilitieResourcesUseCase
import com.technicaltest.technicaltest.dataSources.meep.Interfaces.MeetApi
import com.technicaltest.technicaltest.dataSources.meep.dataSources.mobilitieResources.MobilitieResourcesDataSource
import com.technicaltest.technicaltest.utilities.app.ApplicationResourcesUtilities
import com.technicaltest.technicaltest.utilities.helpers.CustomAlertDialog
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule(private val technicalTestApplication: TechnicalTestApplication) {

    @Provides
    internal fun provideTechnicalTestApplication(): TechnicalTestApplication {
        return technicalTestApplication
    }

    @Provides
    @Singleton
    internal fun provideMobilitieResourcesDataSource(meetApi: MeetApi): MobilitieResourcesDataSource {
        return MobilitieResourcesDataSource(meetApi)
    }

    @Provides
    @Singleton
    internal fun provideMobilitieResourcesUseCase(
        mobilitieResourcesDataSource: MobilitieResourcesDataSource
    ): MobilitieResourcesUseCase {
        return MobilitieResourcesUseCase(mobilitieResourcesDataSource)
    }

    @Provides
    @Singleton
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitClientMeepApi(): MeetApi {
        return RetrofitClient.provideRetrofitClientMeetApi()
    }

    @Provides
    @Singleton
    internal fun provideMapViewModel(): MapViewModel {
        return MapViewModel()
    }

    @Provides
    @Singleton
    internal fun provideCustomAlertDialog(): CustomAlertDialog {
        return CustomAlertDialog()
    }

    @Provides
    @Singleton
    internal fun provideLayoutInflater(technicalTestApplication: TechnicalTestApplication): LayoutInflater {
        return technicalTestApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    @Provides
    @Singleton
    internal fun provideApplicationResourcesUtilities(technicalTestApplication: TechnicalTestApplication): ApplicationResourcesUtilities {
        return ApplicationResourcesUtilities(technicalTestApplication)
    }
}