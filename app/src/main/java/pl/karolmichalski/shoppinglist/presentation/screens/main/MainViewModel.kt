package pl.karolmichalski.shoppinglist.presentation.screens.main

import android.app.Application
import android.arch.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import pl.karolmichalski.shoppinglist.data.models.Product
import pl.karolmichalski.shoppinglist.presentation.App

class MainViewModel(application: Application) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			return MainViewModel(application as App) as T
		}
	}

	private val selectedProducts = HashSet<String>()

	val productName = MutableLiveData<String>()

	val productList = MutableLiveData<List<Product>>().apply { value = ArrayList() }

	private val productsRepository by lazy { (application as App).productsRepository }

	fun getProducts(owner: LifecycleOwner) {
		productsRepository.getAll().observe(owner, Observer { savedProductList ->
			savedProductList?.map { it.isChecked = selectedProducts.contains(it.key) }
			productList.value = savedProductList
		})
	}

	fun addProduct() {
		productName.value?.let {
			productsRepository.insert(it)
		}
	}

	fun invalidateProductSelection(product: Product) {
		product.isChecked = product.isChecked.not()
		if (product.isChecked)
			selectedProducts.add(product.key)
		else
			selectedProducts.remove(product.key)
	}

	fun removeCheckedProducts() {
		productList.value?.forEach { product ->
			if (product.isChecked)
				productsRepository.delete(product)
		}
	}

	fun getCheckedProductsCount(): Int {
		var count = 0
		productList.value?.forEach { product ->
			if (product.isChecked)
				count++
		}
		return count
	}

	fun deselectAllProducts() {
		productList.value?.forEach { product ->
			if (product.isChecked) {
				product.isChecked = false
				invalidateProductSelection(product) //database needs to know that product is unchecked
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