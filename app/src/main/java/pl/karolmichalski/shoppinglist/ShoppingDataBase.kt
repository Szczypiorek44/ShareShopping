package pl.karolmichalski.shoppinglist

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.karolmichalski.shoppinglist.models.Product

@Database(entities = [(Product::class)], version = 1)
abstract class ShoppingDataBase : RoomDatabase() {

	abstract fun productsDao(): ProductsDAO

	companion object {

		private var INSTANCE: ShoppingDataBase? = null

		fun getInstance(context: Context): ShoppingDataBase {
			if (INSTANCE == null) {
				synchronized(ShoppingDataBase::class) {
					INSTANCE = Room.databaseBuilder(context.applicationContext, ShoppingDataBase::class.java, "shopping.db").build()
				}
			}
			return INSTANCE!!
		}

		fun destroyInstance() {
			INSTANCE = null
		}
	}
}