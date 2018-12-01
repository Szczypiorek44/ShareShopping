package pl.karolmichalski.shareshopping.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("/search/repositories")
	fun findRepos(@Query("q") keyword: String): Single<String>

}