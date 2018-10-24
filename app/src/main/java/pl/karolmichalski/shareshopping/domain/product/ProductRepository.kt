package pl.karolmichalski.shareshopping.domain.product

import androidx.lifecycle.LiveData
import pl.karolmichalski.shareshopping.data.models.Product

interface ProductRepository {

	fun getAll(): LiveData<List<Product>>

	fun insert(name: String)

	fun update(product: Product)

	fun delete(product: Product)
}