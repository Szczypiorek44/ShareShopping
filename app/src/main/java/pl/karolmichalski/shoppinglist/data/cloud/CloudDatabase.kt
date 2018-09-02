package pl.karolmichalski.shoppinglist.data.cloud

import com.androidhuman.rxfirebase2.database.rxRemoveValue
import com.androidhuman.rxfirebase2.database.rxSetValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import pl.karolmichalski.shoppinglist.data.models.Product

class CloudDatabase private constructor() : CloudDatabaseDAO {

	private val databaseRef = FirebaseDatabase.getInstance().reference
	private val productListDir = databaseRef.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("products")

	private lateinit var keyDir: DatabaseReference

	fun getDao(): CloudDatabaseDAO {
		return this
	}

	override fun generateKey(): String? {
		keyDir = productListDir.push()
		return keyDir.key
	}

	override fun insert(product: Product): Completable {
		return keyDir.rxSetValue(product.name)
	}

	override fun delete(product: Product): Completable {
		return productListDir.child(product.key).rxRemoveValue()
	}

	companion object {
		private var INSTANCE: CloudDatabase? = null

		fun getInstance(): CloudDatabase {
			if (INSTANCE == null)
				INSTANCE = CloudDatabase()
			return INSTANCE!!
		}
	}

}