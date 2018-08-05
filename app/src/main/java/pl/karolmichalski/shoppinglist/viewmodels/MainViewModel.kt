package pl.karolmichalski.shoppinglist.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import pl.karolmichalski.shoppinglist.data.ProductsRepository
import pl.karolmichalski.shoppinglist.models.Product

class MainViewModel(application: Application) : AndroidViewModel(application) {

	val productName = MutableLiveData<String>()

	val productList = MutableLiveData<List<Product>>().apply { value = ArrayList() }

	private val productsRepository by lazy { ProductsRepository(application) }

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

	fun updateProduct(product: Product) {
		productsRepository.update(product)
	}

	fun removeCheckedProducts() {
		productList.value?.forEach { product ->
			if (product.checked)
				productsRepository.delete(product)
		}
	}

	fun getCheckedProductsCount(): Int {
		var count = 0
		productList.value?.forEach { product ->
			if (product.checked)
				count++
		}
		return count
	}

	fun deselectAllProducts() {
		productList.value?.forEach { product ->
			if (product.checked) {
				product.checked = false
				updateProduct(product) //database needs to know that product is unchecked
			}
		}
	}

	fun clearProductName() {
		productName.value = ""
	}

	fun isUserLogged(): Boolean {
		return FirebaseAuth.getInstance().currentUser != null
	}

}