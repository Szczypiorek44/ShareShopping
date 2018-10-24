package pl.karolmichalski.shareshopping.data.product

import android.content.Context
import androidx.room.Room
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import pl.karolmichalski.shareshopping.data.product.cloud.CloudDatabase
import pl.karolmichalski.shareshopping.data.product.cloud.CloudDatabaseImpl
import pl.karolmichalski.shareshopping.data.product.local.LocalDatabase
import pl.karolmichalski.shareshopping.data.product.local.LocalDatabaseDAO
import pl.karolmichalski.shareshopping.domain.product.ProductRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class ProductModule(private val context: Context) {

	@Provides
	@Singleton
	fun provideProductsRepository(
			@Named("localDatabase") localDatabase: LocalDatabaseDAO,
			@Named("cloudDatabase") cloudDatabase: CloudDatabase): ProductRepository {
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
	fun provideCloudDatabase(): CloudDatabase {
		return CloudDatabaseImpl(FirebaseDatabase.getInstance())
	}

}