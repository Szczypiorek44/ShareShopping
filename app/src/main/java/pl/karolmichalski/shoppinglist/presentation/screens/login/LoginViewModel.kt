package pl.karolmichalski.shoppinglist.presentation.screens.login

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.rxkotlin.subscribeBy
import pl.karolmichalski.shoppinglist.domain.user.UserRepository
import pl.karolmichalski.shoppinglist.presentation.App
import javax.inject.Inject

class LoginViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return LoginViewModel(application as App) as T
		}
	}

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

	fun logInWithEmailAndPassword() {
		userRepository.login(email.value, password.value)
				.doOnSubscribe { isLoading.value = true }
				.doFinally { isLoading.value = false }
				.subscribeBy(
						onSuccess = { loginSuccess.value = true },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

	fun registerWithEmailAndPassword() {
		userRepository.register(email.value, password.value)
				.doOnSubscribe { isLoading.value = true }
				.doFinally { isLoading.value = false }
				.subscribeBy(
						onSuccess = { loginSuccess.value = true },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

	fun isUserLogged(): Boolean {
		return userRepository.getCurrentUser() != null
	}


}