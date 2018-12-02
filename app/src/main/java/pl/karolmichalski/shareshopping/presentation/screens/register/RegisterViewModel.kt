package pl.karolmichalski.shareshopping.presentation.screens.register

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

class RegisterViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return RegisterViewModel(application as App) as T
		}
	}

	val login = MutableLiveData<String>()
	val email = MutableLiveData<String>()
	val password = MutableLiveData<String>()
	val repeatPassword = MutableLiveData<String>()
	val isLoading = MutableLiveData<Boolean>().apply { value = false }

	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var apiRepository: ApiRepository

	init {
		app.appComponent.inject(this)
	}

	fun register(onSuccess: () -> Unit) {
		apiRepository.register(login.value, email.value, password.value, repeatPassword.value)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.value = true }
				.doFinally { isLoading.value = false }
				.subscribeBy(
						onSuccess = { logIn(onSuccess) },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

	private fun logIn(onSuccess: () -> Unit) {
		apiRepository.login(login.value, password.value)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.value = true }
				.doFinally { isLoading.value = false }
				.subscribeBy(
						onSuccess = { onSuccess() },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}


}