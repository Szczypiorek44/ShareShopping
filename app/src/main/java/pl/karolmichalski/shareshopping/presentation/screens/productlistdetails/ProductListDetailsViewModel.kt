package pl.karolmichalski.shareshopping.presentation.screens.productlistdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.karolmichalski.shareshopping.presentation.App

class ProductListDetailsViewModel(app: App): ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return ProductListDetailsViewModel(application as App) as T
		}
	}


}