package pl.karolmichalski.shoppinglist.models

import android.arch.persistence.room.*

@Entity(tableName = "products")
class Product(@PrimaryKey
			  @ColumnInfo(name = "key") val key: String,
			  @ColumnInfo(name = "name") val name: String,
			  @ColumnInfo(name = "status") var status: Status) {
	@Ignore
	var checked: Boolean = false

	enum class Status { ADDED, SYNCED, DELETED }

	class StatusConverter {
		@TypeConverter
		fun toStatus(status: Int): Status {
			return when (status) {
				Status.ADDED.ordinal -> Status.ADDED
				Status.SYNCED.ordinal -> Status.SYNCED
				Status.DELETED.ordinal -> Status.DELETED
				else -> throw IllegalArgumentException("Could not recognize status")
			}
		}

		@TypeConverter
		fun toInt(status: Status): Int {
			return status.ordinal
		}
	}
}