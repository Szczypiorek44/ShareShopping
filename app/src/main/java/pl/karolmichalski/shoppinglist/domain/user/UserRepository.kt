package pl.karolmichalski.shoppinglist.domain.user

import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single

interface UserRepository {
	fun logIn(email: String?, password: String?): Single<FirebaseUser>

	fun register(email: String?, password: String?): Single<FirebaseUser>

	fun getCurrentUser(): FirebaseUser?

	fun logOut()

}