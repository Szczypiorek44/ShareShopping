package pl.karolmichalski.shoppinglist.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pl.karolmichalski.shoppinglist.data.models.Product

@Database(entities = [(Product::class)], version = 2)
abstract class LocalDatabase : RoomDatabase() {

	abstract fun productsDao(): LocalDatabaseDAO

}