package pl.karolmichalski.shoppinglist.data.user

import com.androidhuman.rxfirebase2.auth.rxCreateUserWithEmailAndPassword
import com.androidhuman.rxfirebase2.auth.rxSignInWithEmailAndPassword
import com.androidhuman.rxfirebase2.auth.rxSignOut
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable
import io.reactivex.Single
import pl.karolmichalski.shoppinglist.domain.user.UserRepository

class UserRepositoryImpl(
		private val firebaseAuth: FirebaseAuth) : UserRepository {


	override fun login(email: String?, password: String?): Single<FirebaseUser> {
		return when {
			email.isNullOrBlank() -> Single.fromCallable { throw Exception("Enter email!") }
			password.isNullOrEmpty() -> Single.fromCallable { throw Exception("Enter Password!") }
			else -> firebaseAuth.rxSignInWithEmailAndPassword(email!!, password!!)
		}
	}

	override fun register(email: String?, password: String?): Single<FirebaseUser> {
		return when {
			email.isNullOrBlank() -> Single.fromCallable { throw Exception("Enter email!") }
			password.isNullOrEmpty() -> Single.fromCallable { throw Exception("Enter Password!") }
			else -> firebaseAuth.rxCreateUserWithEmailAndPassword(email!!, password!!)
		}
	}

	override fun logout(): Completable {
		return firebaseAuth.rxSignOut()
	}
}