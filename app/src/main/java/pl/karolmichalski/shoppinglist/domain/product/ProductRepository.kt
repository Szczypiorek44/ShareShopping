package pl.karolmichalski.shoppinglist.domain.product

import androidx.lifecycle.LiveData
import pl.karolmichalski.shoppinglist.data.models.Product

interface ProductRepository {

	fun getAll(): LiveData<List<Product>>

	fun insert(name: String)

	fun update(product: Product)

	fun delete(product: Product)
}