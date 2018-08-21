package pl.karolmichalski.shoppinglist.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.karolmichalski.shoppinglist.data.models.Product

@Database(entities = [(Product::class)], version = 1)
abstract class LocalDatabase : RoomDatabase() {

	abstract fun productsDao(): LocalDatabaseDAO

	companion object {

		private var INSTANCE: LocalDatabase? = null

		fun getInstance(context: Context): LocalDatabase {
			if (INSTANCE == null) {
				synchronized(LocalDatabase::class) {
					INSTANCE = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "shopping.db").build()
				}
			}
			return INSTANCE!!
		}

		fun destroyInstance() {
			INSTANCE = null
		}
	}
}