package pl.karolmichalski.shareshopping.data

import android.content.Context
import io.reactivex.Single
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.data.exceptions.BlankInputException
import pl.karolmichalski.shareshopping.data.models.ProductList
import pl.karolmichalski.shareshopping.domain.ApiRepository

class ApiRepositoryImpl(private val context: Context,
						private val apiInterface: ApiInterface,
						private val sharedPrefs: SharedPrefs) : ApiRepository {

	override fun register(login: String?, email: String?, password: String?, repeatPassword: String?): Single<Boolean> {
		return when {
			login.isNullOrBlank() -> Single.error(BlankInputException("Enter login!"))
			email.isNullOrBlank() -> Single.error(BlankInputException("Enter email!"))
			password.isNullOrEmpty() -> Single.error(BlankInputException("Enter password!"))
			repeatPassword.isNullOrEmpty() -> Single.error(BlankInputException("Enter repeated password!"))
			password != repeatPassword -> Single.error(BlankInputException("Entered passwords do not match!"))
			else -> apiInterface.register(login, email, password)
		}
	}

	override fun login(login: String?, password: String?): Single<String> {
		return when {
			login.isNullOrBlank() -> Single.error(BlankInputException("Enter login!"))
			password.isNullOrEmpty() -> Single.error(BlankInputException("Enter password!"))
			else -> apiInterface.login(login, password).map { uid ->
				if (uid.isValidUid()) {
					uid.also { sharedPrefs.saveUid(uid) }
				} else {
					error(context.getString(R.string.invalid_login_or_password))
				}
			}
		}
	}

	override fun getProductLists(): Single<List<ProductList>> {
		val uid = sharedPrefs.getUid()
		return if (uid.isBlank())
			Single.error(BlankInputException("Uid is empty"))
		else
			apiInterface.getUserLists(uid)
	}

	private fun String.isValidUid(): Boolean {
		return this != "00000000-0000-0000-0000-000000000000"
	}
}