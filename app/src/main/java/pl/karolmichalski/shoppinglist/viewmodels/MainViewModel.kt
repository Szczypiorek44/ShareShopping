package pl.karolmichalski.shoppinglist.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pl.karolmichalski.shoppinglist.models.Product

class MainViewModel : ViewModel() {
	val newProductName = MutableLiveData<String>()

	fun getNewProduct(): Product {
		return Product(newProductName.value!!)
	}

	fun resetNewProductName(){
		newProductName.value = ""
	}
}