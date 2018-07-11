package pl.karolmichalski.shoppinglist.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
data class Product(@PrimaryKey
				   @ColumnInfo(name = "name")
				   var name: String,
				   @Ignore
				   var checked: Boolean = false){

	constructor():this("")
}