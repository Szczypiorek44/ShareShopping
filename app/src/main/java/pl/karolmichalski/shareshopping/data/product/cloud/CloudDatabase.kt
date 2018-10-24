package pl.karolmichalski.shareshopping.data.product.cloud

import io.reactivex.Completable
import pl.karolmichalski.shareshopping.data.models.Product

interface CloudDatabase {
	fun generateKey(): String?

	fun insert(product: Product): Completable

	fun delete(product: Product): Completable
}