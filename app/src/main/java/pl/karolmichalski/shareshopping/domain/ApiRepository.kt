package pl.karolmichalski.shareshopping.domain

import io.reactivex.Single
import pl.karolmichalski.shareshopping.data.models.ProductList

interface ApiRepository {

	fun register(login: String?,
				 email: String?,
				 password: String?,
				 repeatPassword: String?)
			: Single<Boolean>

	fun login(login: String?,
			  password: String?)
			: Single<String>

	fun getUserLists(uid: String?): Single<List<ProductList>>

}