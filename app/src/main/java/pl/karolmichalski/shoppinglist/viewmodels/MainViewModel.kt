package pl.karolmichalski.shoppinglist.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import pl.karolmichalski.shoppinglist.data.ProductsRepository
import pl.karolmichalski.shoppinglist.models.Product

class MainViewModel(application: Application) : AndroidViewModel(application) {

	val productName = MutableLiveData<String>()

	val productList = MutableLiveData<List<Product>>().apply { value = ArrayList() }

	private val productsRepository = ProductsRepository(application)

	fun getProducts(owner: LifecycleOwner) {
		productsRepository.getAll().observe(owner, Observer {
			productList.value = it
		})
	}

	fun addProduct() {
		productName.value?.let {
			productsRepository.insert(it)
		}
	}

	fun removeProduct(product: Product) {
		productsRepository.delete(product)
	}

	fun clearProductName() {
		productName.value = ""
	}
}