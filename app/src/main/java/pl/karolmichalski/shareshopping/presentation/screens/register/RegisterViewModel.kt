package pl.karolmichalski.shareshopping.presentation.screens.register

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.karolmichalski.shareshopping.domain.user.UserRepository
import pl.karolmichalski.shareshopping.presentation.App
import javax.inject.Inject

class RegisterViewModel(app: App) {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return RegisterViewModel(application as App) as T
		}
	}

	val login = MutableLiveData<String>()
	val email = MutableLiveData<String>()
	val password = MutableLiveData<String>()
	val isLoading = MutableLiveData<Boolean>().apply { value = false }

	val loginSuccess = MutableLiveData<Boolean>()
	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var userRepository: UserRepository

	init {
		app.appComponent.inject(this)
	}

}