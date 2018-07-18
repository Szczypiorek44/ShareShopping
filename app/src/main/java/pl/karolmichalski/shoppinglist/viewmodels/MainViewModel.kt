package pl.karolmichalski.shoppinglist.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import pl.karolmichalski.shoppinglist.data.ProductsRepository
import pl.karolmichalski.shoppinglist.models.Product

class MainViewModel(application: Application) : AndroidViewModel(application) {

	val newProductName = MutableLiveData<String>()

	private val repository = ProductsRepository(application)
	private val cloudDatabase = FirebaseDatabase.getInstance().reference

	fun getCreatedProduct(): Product {
		val product = Product(newProductName.value!!)
		repository.insert(product)

		return product
	}

	fun clearNewProductName() {
		newProductName.value = ""
	}

	fun getProducts(): LiveData<List<Product>> {
		return repository.getAll()
	}

//	private fun addProduct(){
//		cloudDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("products").push().rxSetValue(product.name)
//				.subscribeBy(
//						onComplete = {
//
//						},
//						onError = {
//
//						}
//				)
//	}
}