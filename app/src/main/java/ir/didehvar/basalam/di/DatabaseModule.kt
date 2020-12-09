package ir.didehvar.basalam.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): ir.didehvar.basalam.data.local.Database = Room.databaseBuilder(context, ir.didehvar.basalam.data.local.Database::class.java, "database.db").build()
}