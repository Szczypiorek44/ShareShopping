package pl.karolmichalski.shoppinglist.presentation.screens.main

import android.app.Application
import android.arch.lifecycle.*
import pl.karolmichalski.shoppinglist.data.models.Product
import pl.karolmichalski.shoppinglist.presentation.App

class MainViewModel(application: Application) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			return MainViewModel(application as App) as T
		}
	}

	val productName = MutableLiveData<String>()

	val productList = MutableLiveData<List<Product>>().apply { value = ArrayList() }

	private val selectedProductsSet = HashSet<String>()

	private val productsRepository = (application as App).productsRepository

	fun getProducts(owner: LifecycleOwner) {
		productsRepository.getAll().observe(owner, Observer { savedProductList ->
			savedProductList?.map { it.isChecked = selectedProductsSet.contains(it.key) }
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
			selectedProductsSet.add(product.key)
		else
			selectedProductsSet.remove(product.key)
	}

	fun removeCheckedProducts() {
		productList.value?.forEach { product ->
			if (selectedProductsSet.contains(product.key)) {
				productsRepository.delete(product)
				selectedProductsSet.remove(product.key)
			}
		}
	}

	fun getCheckedProductsCount(): Int {
		return selectedProductsSet.size
	}

	fun deselectAllProducts() {
		productList.value?.forEach { product ->
			if (selectedProductsSet.contains(product.key)) {
				product.isChecked = false
				selectedProductsSet.remove(product.key)
			}
		}
	}

	fun clearProductName() {
		productName.value = ""
	}

}