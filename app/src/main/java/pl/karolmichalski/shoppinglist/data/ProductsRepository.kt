package pl.karolmichalski.shoppinglist.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.androidhuman.rxfirebase2.database.rxRemoveValue
import com.androidhuman.rxfirebase2.database.rxSetValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.shoppinglist.models.Product

class ProductsRepository(application: Application) {

	private val localDatabase = LocalDatabase.getInstance(application).productsDao()
	private val cloudDatabase = FirebaseDatabase.getInstance().reference

	private val user = FirebaseAuth.getInstance().currentUser

	fun getAll(): LiveData<List<Product>> {
		return localDatabase.getAll()
	}

	fun insert(name: String) {
		val cloudProducts = cloudDatabase.child("users").child(user!!.uid).child("products").push()
		val key = cloudProducts.key!!
		val product = Product(key, name)
		Completable.fromAction { localDatabase.insert(product) }
				.andThen(cloudProducts.rxSetValue(product.name))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeBy(
						onComplete = {
							Log.d("awda", "awdaw")
						},
						onError = {
							Log.d("awda", "awdaw")
						}
				)
	}

	fun delete(product: Product) {
		Completable.fromAction { localDatabase.delete(product) }
				.andThen(cloudDatabase.child("users").child(user!!.uid).child("products").child(product.key).rxRemoveValue())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeBy(
						onComplete = {
							Log.d("awda", "awdaw")
						},
						onError = {
							Log.d("awda", "awdaw")
						}
				)
	}

//	fun merge(clientSingle: Single<ClientResponse>, statusesSingle: Single<List<LabelValue>>): Single<RequestMerger> {
//		return Single.zip(clientSingle, statusesSingle, BiFunction { client, statuses ->
//			client.client.applicationList?.let {
//				for (application in it)
//					application.setStatuses(statuses)
//			}
//			RequestMerger(client.client)
//		})
//	}

}