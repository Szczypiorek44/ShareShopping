package pl.karolmichalski.shareshopping.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("register")
	fun register(@Query("login") login: String,
				 @Query("email") email: String,
				 @Query("password") password: String)
			: Single<String>

	@GET("login")
	fun login(@Query("login") login: String,
			  @Query("password") password: String)
			: Single<String>

}