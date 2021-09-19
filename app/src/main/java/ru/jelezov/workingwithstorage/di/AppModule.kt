package ru.jelezov.workingwithstorage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.jelezov.workingwithstorage.data.cursor.PersonSQLiteOpenHelper
import ru.jelezov.workingwithstorage.data.room.RoomDataSource
import ru.jelezov.workingwithstorage.data.room.RoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRoomDataSource(roomDatabase: RoomDatabase, personSQLiteOpenHelper: PersonSQLiteOpenHelper) : RoomDataSource {
        return RoomDataSource(roomDatabase, personSQLiteOpenHelper)
    }

    @Provides
    fun provideContext(
        @ApplicationContext app: Context
    ) : PersonSQLiteOpenHelper {
        return PersonSQLiteOpenHelper(app)
    }

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RoomDatabase::class.java,
        "myDb"
    ).build()

    @Provides
    @ApplicationContext
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}