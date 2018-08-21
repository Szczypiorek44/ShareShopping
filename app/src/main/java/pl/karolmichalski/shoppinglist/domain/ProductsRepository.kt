package pl.karolmichalski.shoppinglist.domain

import android.arch.lifecycle.LiveData
import pl.karolmichalski.shoppinglist.data.models.Product

interface ProductsRepository {

	fun getAll(): LiveData<List<Product>>

	fun insert(name: String)

	fun update(product: Product)

	fun delete(product: Product)
}