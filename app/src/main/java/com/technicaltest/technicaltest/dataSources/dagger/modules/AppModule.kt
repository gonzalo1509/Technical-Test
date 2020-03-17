package com.technicaltest.technicaltest.dataSources.dagger.modules

import com.technicaltest.technicaltest.app.application.TechnicalTestApplication
import com.technicaltest.technicaltest.app.services.retrofit.RetrofitClient
import com.technicaltest.technicaltest.bussiness.api.interfaces.MeetApi
import com.technicaltest.technicaltest.bussiness.useCases.mobilitieResources.MobilitieResourcesUseCase
import com.technicaltest.technicaltest.dataSources.meep.dataSources.mobilitieResources.MobilitieResourcesDataSource
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule (private val technicalTestApplication: TechnicalTestApplication) {

    @Provides
    internal fun provideApplication(): TechnicalTestApplication {
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
}