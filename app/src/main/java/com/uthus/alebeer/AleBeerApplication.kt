package com.uthus.alebeer

import android.app.Application
import com.uthus.alebeer.data.datasource.local.AleBeerLocalDataSourceImpl
import com.uthus.alebeer.data.datasource.remote.AleBeerRemoteDataSource
import com.uthus.alebeer.data.datasource.remote.AleBeerRemoteDataSourceImpl
import com.uthus.alebeer.data.repository.AleBeerRepositoryImpl
import com.uthus.alebeer.domain.usecase.GetBeersUseCaseImpl
import com.uthus.alebeer.domain.usecase.SaveFavoriteUseCaseImpl
import com.uthus.alebeer.util.localdb.BeerRoomDatabase

class AleBeerApplication : Application() {
    private val database: BeerRoomDatabase by lazy { BeerRoomDatabase.getDatabase(this) }
    private val aleBeerLocalDataSource by lazy { AleBeerLocalDataSourceImpl(database.beerDao()) }
    private val aleBeerRemoteDataSource by lazy { AleBeerRemoteDataSourceImpl() }
    private val aleBeerRepository by lazy {
        AleBeerRepositoryImpl(
            localDataSource = aleBeerLocalDataSource,
            remoteDataSource = aleBeerRemoteDataSource
        )
    }
    val getBeersUseCase by lazy { GetBeersUseCaseImpl(aleBeerRepository) }
    val saveFavoriteUseCase by lazy { SaveFavoriteUseCaseImpl(aleBeerRepository) }
}