package pl.karolmichalski.shoppinglist.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import pl.karolmichalski.shoppinglist.models.Product

@Dao
interface LocalDatabaseDAO {

	@Query("Select * from products")
	fun getAll(): LiveData<List<Product>>

	@Insert(onConflict = REPLACE)
	fun insert(product: Product)

	@Delete
	fun delete(product: Product)


}