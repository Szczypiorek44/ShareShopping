package pl.karolmichalski.shareshopping.domain

import io.reactivex.Single

interface ApiRepository {

	fun register(login: String?,
				 email: String?,
				 password: String?,
				 repeatPassword: String?)
			: Single<Boolean>

	fun login(login: String?,
			  password: String?)
			: Single<String>

}