package pl.karolmichalski.shareshopping.data

import io.reactivex.Single
import pl.karolmichalski.shareshopping.data.exceptions.BlankInputException
import pl.karolmichalski.shareshopping.domain.ApiRepository

class ApiRepositoryImpl(private val apiInterface: ApiInterface) : ApiRepository {

	override fun register(login: String?, email: String?, password: String?): Single<String> {
		return when {
			login.isNullOrBlank() -> Single.error(BlankInputException("Enter Login!"))
			email.isNullOrBlank() -> Single.error(BlankInputException("Enter Email!"))
			password.isNullOrEmpty() -> Single.error(BlankInputException("Enter Password!"))
			else -> apiInterface.register(login, email, password)
		}
	}

	override fun login(login: String?, password: String?): Single<String> {
		return when {
			login.isNullOrBlank() -> Single.error(BlankInputException("Enter Login!"))
			password.isNullOrEmpty() -> Single.error(BlankInputException("Enter Password!"))
			else -> apiInterface.login(login, password)
		}
	}
}