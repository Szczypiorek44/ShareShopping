package pl.karolmichalski.shareshopping.data

import android.content.Context
import io.reactivex.Single
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.data.exceptions.BlankInputException
import pl.karolmichalski.shareshopping.domain.ApiRepository

class ApiRepositoryImpl(private val context: Context,
						private val apiInterface: ApiInterface,
						private val sharedPrefs: SharedPrefs) : ApiRepository {

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
			else -> apiInterface.login(login, password).map { guid ->
				if (guid.isValidGuid()) {
					guid.also { sharedPrefs.saveGuid(guid) }
				} else {
					error(context.getString(R.string.invalid_login_or_password))
				}
			}
		}
	}

	private fun String.isValidGuid(): Boolean {
		return this != "00000000-0000-0000-0000-000000000000"
	}
}