package pl.karolmichalski.shoppinglist.data.cloud

import io.reactivex.Completable
import pl.karolmichalski.shoppinglist.data.models.Product

interface CloudDatabaseDAO {
	fun generateKey(): String?

	fun insert(product: Product): Completable

	fun delete(product: Product): Completable
}