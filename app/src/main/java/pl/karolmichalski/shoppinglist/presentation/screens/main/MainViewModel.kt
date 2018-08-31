package pl.karolmichalski.shoppinglist.presentation.screens.main

import android.app.Application
import android.arch.lifecycle.*
import pl.karolmichalski.shoppinglist.data.models.Product
import pl.karolmichalski.shoppinglist.domain.ProductRepository
import pl.karolmichalski.shoppinglist.presentation.App
import javax.inject.Inject

class MainViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			return MainViewModel(application as App) as T
		}
	}

	val productName = MutableLiveData<String>()

	val productList = MutableLiveData<List<Product>>().apply { value = ArrayList() }

	val selectedProducts = HashSet<String>()

	@Inject
	lateinit var productRepository: ProductRepository

	init {
		app.productComponent.inject(this)
	}

	fun getProducts(owner: LifecycleOwner) {
		productRepository.getAll().observe(owner, Observer { savedProductList ->
			savedProductList?.map { it.isChecked = selectedProducts.contains(it.key) }
			productList.value = savedProductList
		})
	}

	fun addProduct(name: String) {
		productRepository.insert(name)
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
			if (selectedProducts.contains(product.key)) {
				productRepository.delete(product)
				selectedProducts.remove(product.key)
			}
		}
	}

	fun deselectAllProducts() {
		productList.value?.forEach { product ->
			if (selectedProducts.contains(product.key)) {
				product.isChecked = false
				selectedProducts.remove(product.key)
			}
		}
	}

	fun clearProductName() {
		productName.value = ""
	}

}