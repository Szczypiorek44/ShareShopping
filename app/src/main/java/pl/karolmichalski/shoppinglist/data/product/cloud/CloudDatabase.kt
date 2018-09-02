package pl.karolmichalski.shoppinglist.data.product.cloud

import io.reactivex.Completable
import pl.karolmichalski.shoppinglist.data.models.Product

interface CloudDatabase {
	fun generateKey(): String?

	fun insert(product: Product): Completable

	fun delete(product: Product): Completable
}