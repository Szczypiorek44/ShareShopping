package pl.karolmichalski.shareshopping.data

import io.reactivex.Single
import pl.karolmichalski.shareshopping.data.models.Product
import pl.karolmichalski.shareshopping.data.models.ProductList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

	@GET("register")
	fun register(@Query("login") login: String,
				 @Query("email") email: String,
				 @Query("password") password: String)
			: Single<Boolean>

	@GET("login")
	fun login(@Query("login") login: String,
			  @Query("password") password: String)
			: Single<String>

	@GET("getUserList")
	fun getUserLists(@Query("userId") uid: String)
			: Single<List<ProductList>>

	@GET("getProductsFromList")
	fun getProductsFromList(@Query("listId") listId: String)
			: Single<List<Product>>

	@GET("addProductToList")
	fun addProductToList(@Query("productName") productName: String,
						 @Query("listId") listId: String)
			: Single<Boolean>

	@GET("createNewList")
	fun createNewList(@Query("userId") uid: String,
					  @Query("listName") listName: String)
			: Single<Boolean>

	@GET("deleteList")
	fun deleteList(@Query("listId") listId: String)
			: Single<Boolean>
}