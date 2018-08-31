package pl.karolmichalski.shoppinglist.data.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.karolmichalski.shoppinglist.data.*
import pl.karolmichalski.shoppinglist.domain.ProductsRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class ProductRepositoryModule(private val context: Context) {

	@Provides
	@Singleton
	fun provideProductsRepository(
			@Named("localDatabase") localDatabase: LocalDatabaseDAO,
			@Named("cloudDatabase") cloudDatabase: CloudDatabaseDAO): ProductsRepository {
		return ProductsRepositoryImpl(localDatabase, cloudDatabase)
	}

	@Provides
	@Singleton
	@Named("localDatabase")
	fun provideLocalDatabase(): LocalDatabaseDAO {
		val database = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "shopping.db")
				.fallbackToDestructiveMigration()
				.build()
		return database.productsDao()
	}

	@Provides
	@Singleton
	@Named("cloudDatabase")
	fun provideCloudDatabase(): CloudDatabaseDAO {
		return CloudDatabase.getInstance().getDao()
	}
}