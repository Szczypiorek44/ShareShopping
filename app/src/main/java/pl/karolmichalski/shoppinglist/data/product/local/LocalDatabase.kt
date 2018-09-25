package pl.karolmichalski.shoppinglist.data.product.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.karolmichalski.shoppinglist.data.models.Product

@Database(entities = [(Product::class)], version = 2)
abstract class LocalDatabase : RoomDatabase() {

	abstract fun productsDao(): LocalDatabaseDAO

}