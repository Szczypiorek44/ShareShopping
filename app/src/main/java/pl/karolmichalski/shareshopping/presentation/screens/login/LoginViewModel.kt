package pl.karolmichalski.shareshopping.presentation.screens.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.shareshopping.domain.ApiRepository
import pl.karolmichalski.shareshopping.presentation.App
import javax.inject.Inject

class LoginViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return LoginViewModel(application as App) as T
		}
	}

	val login = MutableLiveData<String>()
	val password = MutableLiveData<String>()
	val isLoading = MutableLiveData<Boolean>().apply { value = false }

	val loginSuccess = MutableLiveData<Boolean>()
	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var apiRepository: ApiRepository

	init {
		app.appComponent.inject(this)
	}

	fun isUserLogged(): Boolean {
		return false
	}

	fun logIn() {
		apiRepository.login(login.value, password.value)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.value = true }
				.doFinally { isLoading.value = false }
				.subscribeBy(
						onSuccess = { loginSuccess.value = true },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}


}