package pl.karolmichalski.shoppinglist.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.androidhuman.rxfirebase2.auth.rxCreateUserWithEmailAndPassword
import com.androidhuman.rxfirebase2.auth.rxSignInWithEmailAndPassword
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxkotlin.subscribeBy

class LoginViewModel : ViewModel() {

	val email = MutableLiveData<String>()
	val password = MutableLiveData<String>()

	val loadingVisibility = MutableLiveData<Int>().apply { value = View.GONE }
	val loginSuccess = MutableLiveData<Boolean>()
	val errorMessage = MutableLiveData<String>()

	private val firebaseAuth = FirebaseAuth.getInstance()

	fun signInWithEmailAndPassword() {
		firebaseAuth.rxSignInWithEmailAndPassword(email.value!!, password.value!!)
				.doOnSubscribe { loadingVisibility.value = View.VISIBLE }
				.doFinally { loadingVisibility.value = View.GONE }
				.subscribeBy(
						onSuccess = { loginSuccess.value = true },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

	fun createUserWithEmailAndPassword() {
		firebaseAuth.rxCreateUserWithEmailAndPassword(email.value!!, password.value!!)
				.doOnSubscribe { loadingVisibility.value = View.VISIBLE }
				.doFinally { loadingVisibility.value = View.GONE }
				.subscribeBy(
						onSuccess = { loginSuccess.value = true },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}


}