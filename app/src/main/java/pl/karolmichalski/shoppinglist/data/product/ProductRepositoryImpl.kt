package pl.karolmichalski.shoppinglist.data.product

import android.arch.lifecycle.LiveData
import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.shoppinglist.data.models.Product
import pl.karolmichalski.shoppinglist.data.product.cloud.CloudDatabase
import pl.karolmichalski.shoppinglist.data.product.local.LocalDatabaseDAO
import pl.karolmichalski.shoppinglist.domain.product.ProductRepository

class ProductRepositoryImpl(
		private val localDatabase: LocalDatabaseDAO,
		private val cloudDatabase: CloudDatabase) : ProductRepository {

	override fun getAll(): LiveData<List<Product>> {
		return localDatabase.getAll()
	}

	override fun insert(name: String) {
		cloudDatabase.generateKey()?.let { key ->
			val product = Product(key, name, Product.Status.ADDED)
			Completable.fromAction { localDatabase.insert(product) }
					.andThen(cloudDatabase.insert(product))
					.subscribeOn(Schedulers.io())
					.observeOn(Schedulers.io())
					.subscribeBy(onComplete = {
						product.status = Product.Status.SYNCED
						localDatabase.update(product)
					})
		}

	}

	override fun update(product: Product) {
		Completable.fromAction { localDatabase.update(product) }
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe()
	}

	override fun delete(product: Product) {
		Completable.fromAction { localDatabase.delete(product) }
				.andThen(cloudDatabase.delete(product))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeBy(
						onComplete = { Log.d("awda", "awdaw") },
						onError = { Log.d("awda", "awdaw") }
				)
	}
}