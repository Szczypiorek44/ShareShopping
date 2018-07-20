package pl.karolmichalski.shoppinglist.data

import io.reactivex.Completable
import pl.karolmichalski.shoppinglist.models.Product

interface CloudDatabaseDAO {
	fun generateKey(): String?

	fun insert(product: Product): Completable

	fun delete(product: Product): Completable
}