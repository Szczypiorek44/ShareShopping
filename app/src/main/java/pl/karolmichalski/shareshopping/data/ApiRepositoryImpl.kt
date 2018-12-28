package pl.karolmichalski.shareshopping.data

import android.content.Context
import io.reactivex.Single
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.data.exceptions.BlankInputException
import pl.karolmichalski.shareshopping.data.models.Product
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

	override fun getProductsFromList(listId: String): Single<List<Product>> {
		return apiInterface.getProductsFromList(listId)
	}

	override fun addProductToList(productName: String?, listId: String): Single<Boolean> {
		return if (productName.isNullOrBlank())
			Single.error(BlankInputException("Enter product name!"))
		else
			apiInterface.addProductToList(productName, listId)
	}

	private fun String.isValidUid(): Boolean {
		return this != "00000000-0000-0000-0000-000000000000"
	}

	override fun createNewList(listName: String?): Single<Boolean> {
		val uid = sharedPrefs.getUid()
		return when {
			uid.isBlank() -> {
				Single.error(BlankInputException("Uid is empty"))
			}
			listName.isNullOrBlank() -> {
				Single.error(BlankInputException("List name is empty"))
			}
			else -> apiInterface.createNewList(uid, listName)
		}
	}

	override fun deleteList(listId: String): Single<Boolean> {
		val uid = sharedPrefs.getUid()
		return apiInterface.deleteList(uid, listId).map { result ->
			if (result)
				result
			else
				error(context.getString(R.string.invalid_login_or_password))
		}
	}

	override fun sync(listId: String): Single<List<Product>> {
		return apiInterface.sync(listId)
	}

	override fun share(listId: String): Single<Boolean> {
		val uid = sharedPrefs.getUid()
		return apiInterface.share(uid, listId)
	}
}