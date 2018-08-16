package pl.karolmichalski.shoppinglist.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
class Product(@PrimaryKey
			  @ColumnInfo(name = "key") val key: String,
			  @ColumnInfo(name = "name") val name: String,
			  @ColumnInfo(name = "status") var status: Int) {
	@ColumnInfo(name = "checked")
	var checked: Boolean = false

	class Status {
		companion object {
			const val ADDED = 0
			const val SYNCED = 1
			const val DELETED = 2
		}
	}
}