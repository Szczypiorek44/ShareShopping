package pl.karolmichalski.shoppinglist.data

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseRepository {

	private val mAuth by lazy { FirebaseAuth.getInstance() }

	fun getCurrentUser(): FirebaseUser? {
		return mAuth.currentUser
	}

	fun createUserWithEmailAndPassword(email: String, password: String) {
		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener {
					if (it.isSuccessful)
						Log.d(TAG, "createUserWithEmail:success")
					else
						Log.w(TAG, "createUserWithEmail:failure", it.exception)
				}
	}

	fun signInWithEmailAndPassword(email: String, password: String) {
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener {
					if (it.isSuccessful)
						Log.d(TAG, "signInWithEmail:success")
					else
						Log.w(TAG, "signInWithEmail:failure", it.exception)
				}
	}

	companion object {



	}


}