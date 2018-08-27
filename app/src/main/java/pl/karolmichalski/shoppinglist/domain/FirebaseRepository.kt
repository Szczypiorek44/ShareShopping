package pl.karolmichalski.shoppinglist.domain

import io.reactivex.Single

interface FirebaseRepository {
	fun getLoginSingle(): Single<Unit>
}