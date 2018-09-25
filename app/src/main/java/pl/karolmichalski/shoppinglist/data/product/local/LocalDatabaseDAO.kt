package pl.karolmichalski.shoppinglist.data.product.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import pl.karolmichalski.shoppinglist.data.models.Product

@Dao
interface LocalDatabaseDAO {

	@Query("Select * from products")
	fun getAll(): LiveData<List<Product>>

	@Insert(onConflict = REPLACE)
	fun insert(product: Product)

	@Update
	fun update(product: Product)

	@Delete
	fun delete(product: Product)


}