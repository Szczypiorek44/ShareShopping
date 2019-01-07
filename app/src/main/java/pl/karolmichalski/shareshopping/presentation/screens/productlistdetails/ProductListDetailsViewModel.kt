package pl.karolmichalski.shareshopping.presentation.screens.productlistdetails

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.shareshopping.data.models.Product
import pl.karolmichalski.shareshopping.domain.ApiRepository
import pl.karolmichalski.shareshopping.presentation.App
import javax.inject.Inject

class ProductListDetailsViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return ProductListDetailsViewModel(application as App) as T
		}
	}

	val productName = MutableLiveData<String>()
	val productList = MutableLiveData<MutableList<Product>>().apply { value = ArrayList() }
	val selectedProducts = HashSet<String>()
	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var apiRepository: ApiRepository

	init {
		app.appComponent.inject(this)
	}

	fun getProducts(listId: String) {
		apiRepository.getProductsFromList(listId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeBy(
						onSuccess = { productList.value = it.toMutableList() },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

	fun addProduct(listId: String) {
		apiRepository.addProductToList(productName.value, listId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeBy(
						onSuccess = {
							productName.value = ""
							getProducts(listId)
						},
						onError = { errorMessage.value = it.localizedMessage }
				)

	}

	fun invalidateProductSelection(product: Product) {
		product.isBought = product.isBought.not()
		apiRepository.sync(product.listId, productList.value)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeBy(
						onSuccess = {
							getProducts(product.listId)

						},
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

}
