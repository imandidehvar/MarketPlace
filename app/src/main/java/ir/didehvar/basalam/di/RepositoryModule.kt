package ir.didehvar.basalam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.didehvar.basalam.data.local.Database
import ir.didehvar.basalam.data.remote.API
import ir.didehvar.basalam.domain.repository.ProductRepository
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(database: Database, api: API) = ProductRepository(database, api)
}