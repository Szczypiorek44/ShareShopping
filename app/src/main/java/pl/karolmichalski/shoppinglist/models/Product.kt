package pl.karolmichalski.shoppinglist.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
class Product(@PrimaryKey
			  @ColumnInfo(name = "key") val key: String,
			  @ColumnInfo(name = "name") val name: String) {
	@Ignore var checked: Boolean = false

}