package pl.karolmichalski.shoppinglist.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
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