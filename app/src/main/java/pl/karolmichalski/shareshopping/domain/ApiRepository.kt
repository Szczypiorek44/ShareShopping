package pl.karolmichalski.shareshopping.domain

import io.reactivex.Single
import pl.karolmichalski.shareshopping.data.models.Product
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

	fun getProductLists(): Single<List<ProductList>>

	fun getProductsFromList(listId: String): Single<List<Product>>

	fun addProductToList(productName: String?,
						 listId: String)
			: Single<Boolean>

	fun createNewList(listName: String?)
			: Single<Boolean>

}