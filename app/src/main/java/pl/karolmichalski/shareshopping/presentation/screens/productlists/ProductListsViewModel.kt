package pl.karolmichalski.shareshopping.presentation.screens.productlists

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.shareshopping.data.models.ProductList
import pl.karolmichalski.shareshopping.domain.ApiRepository
import pl.karolmichalski.shareshopping.presentation.App
import javax.inject.Inject

class ProductListsViewModel(app: App) : ViewModel() {

	class Factory(private val activity: Activity?) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return ProductListsViewModel(activity?.application as App) as T
		}
	}

	val productLists = MutableLiveData<List<ProductList>>().apply { value = ArrayList() }

	val isLoading = MutableLiveData<Boolean>().apply { value = false }
	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var apiRepository: ApiRepository

	init {
		app.appComponent.inject(this)
	}

	fun getProductLists() {
		apiRepository.getProductLists()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.value = true }
				.doFinally { isLoading.value = false }
				.subscribeBy(
						onSuccess = {
							productLists.value = it

						},
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

}