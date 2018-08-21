package pl.karolmichalski.shoppinglist.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
class Product(@ColumnInfo(name = "key") val key: String,
			  @ColumnInfo(name = "name") val name: String,
			  @ColumnInfo(name = "status") var status: Int) {
	@PrimaryKey(autoGenerate = true)
	var id: Int = 0
	@Ignore
	var isChecked: Boolean = false

	class Status {
		companion object {
			const val ADDED = 0
			const val SYNCED = 1
			const val DELETED = 2
		}
	}
}