package pl.karolmichalski.shoppinglist.data.product

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.karolmichalski.shoppinglist.data.product.cloud.CloudDatabase
import pl.karolmichalski.shoppinglist.data.product.cloud.CloudDatabaseDAO
import pl.karolmichalski.shoppinglist.data.product.local.LocalDatabase
import pl.karolmichalski.shoppinglist.data.product.local.LocalDatabaseDAO
import pl.karolmichalski.shoppinglist.domain.product.ProductRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class ProductModule(private val context: Context) {

	@Provides
	@Singleton
	fun provideProductsRepository(
			@Named("localDatabase") localDatabase: LocalDatabaseDAO,
			@Named("cloudDatabase") cloudDatabase: CloudDatabaseDAO): ProductRepository {
		return ProductRepositoryImpl(localDatabase, cloudDatabase)
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