package com.test.currencyratesapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.test.currencyratesapp.presentation.favorite.FavoriteCurrencyRatesViewModel
import com.test.currencyratesapp.presentation.popular.PopularCurrencyRatesViewModel
import com.test.data.CurrencyGatewayImpl
import com.test.data.FiltersGatewayImpl
import com.test.data.api.CurrencyApiDataSource
import com.test.data.api.CurrencyApiDataSourceImpl
import com.test.data.api.CurrencyApiService
import com.test.data.db.CurrenciesDao
import com.test.data.db.CurrencyRoomDB
import com.test.data.preference.PreferencesProvider
import com.test.data.preference.PreferencesProviderImpl
import com.test.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val PREFS_NAME = "sharedPreferences"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CurrencyRoomDB {
        return Room.databaseBuilder(appContext, CurrencyRoomDB::class.java, "RssReader").build()
    }

    @Provides
    fun provideCurrenciesDao(db: CurrencyRoomDB) = db.currenciesDao()

    @Provides
    fun provideCurrencyApiService() = CurrencyApiService()

    @Provides
    fun provideCurrencyApiDataSource(
        @ApplicationContext appContext: Context,
        currencyApiService: CurrencyApiService
    ): CurrencyApiDataSource =
        CurrencyApiDataSourceImpl(appContext, currencyApiService)

    @Provides
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    fun providePreferenceManager(preferences: SharedPreferences): PreferencesProvider =
        PreferencesProviderImpl(preferences)

    @Singleton
    @Provides
    fun provideCurrencyGateway(
        dataSource: CurrencyApiDataSource,
        dao: CurrenciesDao,
        preferencesProvider: PreferencesProvider
    ): CurrencyGateway = CurrencyGatewayImpl(dataSource, dao, preferencesProvider)

    @Provides
    fun provideFiltersGateway(preferencesProvider: PreferencesProvider): FiltersGateway =
        FiltersGatewayImpl(preferencesProvider)

    @Provides
    fun provideGetCurrenciesUseCase(currencyGateway: CurrencyGateway): GetCurrenciesUseCase =
        GetCurrenciesUseCaseImpl(currencyGateway)

    @Provides
    fun provideSortCurrencyRatesUseCase(filtersGateway: FiltersGateway): SortCurrencyRatesUseCase =
        SortCurrencyRatesUseCaseImpl(filtersGateway)

    @Provides
    fun provideGetPopularCurrencyRatesUseCase(
        sortCurrencyRatesUseCase: SortCurrencyRatesUseCaseImpl,
        currencyGateway: CurrencyGateway
    ): GetPopularCurrencyRatesUseCase =
        GetPopularCurrencyRatesUseCaseImpl(sortCurrencyRatesUseCase, currencyGateway)

    @Provides
    fun provideGetFavoriteCurrencyRatesUseCase(
        sortCurrencyRatesUseCase: SortCurrencyRatesUseCaseImpl,
        currencyGateway: CurrencyGateway
    ): GetFavoriteCurrencyRatesUseCase =
        GetFavoriteCurrencyRatesUseCaseImpl(sortCurrencyRatesUseCase, currencyGateway)

    @Provides
    fun provideFavoriteCurrencyRatesViewModel(
        getCurrenciesUseCase: GetCurrenciesUseCase,
        currencyGateway: CurrencyGateway,
        provideGetFavoriteCurrencyRatesUseCase: GetFavoriteCurrencyRatesUseCase
    ) =
        FavoriteCurrencyRatesViewModel(
            getCurrenciesUseCase,
            currencyGateway,
            provideGetFavoriteCurrencyRatesUseCase
        )

    @Provides
    fun providePopularCurrencyRatesViewModel(
        getCurrenciesUseCase: GetCurrenciesUseCase,
        currencyGateway: CurrencyGateway,
        getPopularCurrencyRatesUseCase: GetPopularCurrencyRatesUseCase,
    ) = PopularCurrencyRatesViewModel(
        getCurrenciesUseCase,
        currencyGateway,
        getPopularCurrencyRatesUseCase
    )
}