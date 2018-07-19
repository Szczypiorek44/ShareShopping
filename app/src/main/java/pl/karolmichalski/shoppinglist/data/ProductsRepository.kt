package pl.karolmichalski.shoppinglist.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import pl.karolmichalski.shoppinglist.models.Product

class ProductsRepository(application: Application) {

	private val dao = LocalDataBase.getInstance(application).productsDao()

	fun getAll(): LiveData<List<Product>> {
		return dao.getAll()
	}

	fun insert(product: Product) {
		InsertAsyncTask(dao).execute(product)
	}

	fun delete(product: Product){
		DeleteAsyncTask(dao).execute(product)
	}

	private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: ProductsDAO) : AsyncTask<Product, Void, Void>() {

		override fun doInBackground(vararg params: Product): Void? {
			mAsyncTaskDao.insert(params[0])
			return null
		}
	}

	private class DeleteAsyncTask internal constructor(private val mAsyncTaskDao: ProductsDAO) : AsyncTask<Product, Void, Void>() {

		override fun doInBackground(vararg params: Product): Void? {
			mAsyncTaskDao.delete(params[0])
			return null
		}
	}
}