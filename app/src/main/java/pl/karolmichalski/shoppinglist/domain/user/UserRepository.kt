package pl.karolmichalski.shoppinglist.domain.user

import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
	fun login(email: String?, password: String?): Single<FirebaseUser>

	fun register(email: String?, password: String?): Single<FirebaseUser>

	fun logout(): Completable
}