package pl.karolmichalski.shoppinglist.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

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