package pl.karolmichalski.shoppinglist.data.product.cloud

import com.androidhuman.rxfirebase2.database.rxRemoveValue
import com.androidhuman.rxfirebase2.database.rxSetValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import pl.karolmichalski.shoppinglist.data.models.Product

class CloudDatabaseImpl(
		firebaseDatabase: FirebaseDatabase) : CloudDatabase {

	private val productListDir = firebaseDatabase.reference.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("products")

	private lateinit var keyDir: DatabaseReference

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

}