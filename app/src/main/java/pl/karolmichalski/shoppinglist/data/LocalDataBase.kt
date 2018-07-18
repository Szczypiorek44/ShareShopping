package pl.karolmichalski.shoppinglist.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.karolmichalski.shoppinglist.models.Product

@Database(entities = [(Product::class)], version = 1)
abstract class LocalDataBase : RoomDatabase() {

	abstract fun productsDao(): ProductsDAO

	companion object {

		private var INSTANCE: LocalDataBase? = null

		fun getInstance(context: Context): LocalDataBase {
			if (INSTANCE == null) {
				synchronized(LocalDataBase::class) {
					INSTANCE = Room.databaseBuilder(context.applicationContext, LocalDataBase::class.java, "shopping.db").build()
				}
			}
			return INSTANCE!!
		}

		fun destroyInstance() {
			INSTANCE = null
		}
	}
}