package pl.karolmichalski.shareshopping.presentation.screens.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.karolmichalski.shareshopping.data.SharedPrefs
import pl.karolmichalski.shareshopping.presentation.App
import javax.inject.Inject

class MainViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return MainViewModel(application as App) as T
		}
	}

	@Inject
	lateinit var sharedPrefs: SharedPrefs

	init {
		app.appComponent.inject(this)
	}

	fun logOut() {
		sharedPrefs.saveUid("")
	}
}