package com.test.currencyratesapp.di

import android.content.Context
import androidx.room.Room
import com.test.data.CurrencyGatewayImpl
import com.test.data.api.CurrencyApiDataSource
import com.test.data.api.CurrencyApiDataSourceImpl
import com.test.data.api.CurrencyApiService
import com.test.data.db.CurrenciesDao
import com.test.data.db.CurrencyRoomDB
import com.test.domain.CurrencyGateway
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.GetCurrenciesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CurrencyRoomDB {
        return Room.databaseBuilder(
            appContext,
            CurrencyRoomDB::class.java,
            "RssReader"
        ).build()
    }

    @Provides
    fun provideCurrenciesDao(db: CurrencyRoomDB): CurrenciesDao {
        return db.currenciesDao()
    }

    @Provides
    fun provideCurrencyApiService() = CurrencyApiService()

    @Provides
    fun provideCurrencyApiDataSource(
        @ApplicationContext appContext: Context,
        currencyApiService: CurrencyApiService
        ): CurrencyApiDataSource =
        CurrencyApiDataSourceImpl(appContext, currencyApiService)

    @Provides
    fun provideCurrencyGateway(
        dataSource: CurrencyApiDataSource,
        dao: CurrenciesDao
    ): CurrencyGateway = CurrencyGatewayImpl(dataSource, dao)

    @Provides
    fun provideGetCurrenciesUseCase(currencyGateway: CurrencyGateway): GetCurrenciesUseCase =
        GetCurrenciesUseCaseImpl(currencyGateway)
}