package pl.karolmichalski.shoppinglist.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import pl.karolmichalski.shoppinglist.viewlisteners.LoginCallback

class LoginViewModel : ViewModel() {

	val email = MutableLiveData<String>()
	val password = MutableLiveData<String>()

	private val firebaseAuth = FirebaseAuth.getInstance()

	fun signInWithEmailAndPassword(callback: LoginCallback) {
		firebaseAuth.signInWithEmailAndPassword(email.value!!, password.value!!)
				.addOnCompleteListener { callback.handleResult(it) }
	}

	fun createUserWithEmailAndPassword(callback: LoginCallback) {
		firebaseAuth.createUserWithEmailAndPassword(email.value!!, password.value!!)
				.addOnCompleteListener { callback.handleResult(it) }
	}

	private fun LoginCallback.handleResult(result: Task<AuthResult>) {
		if (result.isSuccessful)
			onSuccess()
		else
			result.exception?.let { onError(it.localizedMessage) }
	}
}