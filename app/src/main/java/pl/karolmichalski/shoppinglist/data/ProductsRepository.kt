package pl.karolmichalski.shoppinglist.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.google.firebase.database.FirebaseDatabase
import pl.karolmichalski.shoppinglist.models.Product

class ProductsRepository(application: Application) {

	private val localDatabase = LocalDatabase.getInstance(application).productsDao()
	private val cloudDatabase = FirebaseDatabase.getInstance().reference

	fun getAll(): LiveData<List<Product>> {
		return localDatabase.getAll()
	}

	fun insert(product: Product) {
		InsertAsyncTask(localDatabase).execute(product)
	}

	fun delete(product: Product){
		DeleteAsyncTask(localDatabase).execute(product)
	}

	private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: LocalDatabaseDAO) : AsyncTask<Product, Void, Void>() {

		override fun doInBackground(vararg params: Product): Void? {
			mAsyncTaskDao.insert(params[0])
			return null
		}
	}

	private class DeleteAsyncTask internal constructor(private val mAsyncTaskDao: LocalDatabaseDAO) : AsyncTask<Product, Void, Void>() {

		override fun doInBackground(vararg params: Product): Void? {
			mAsyncTaskDao.delete(params[0])
			return null
		}
	}
}