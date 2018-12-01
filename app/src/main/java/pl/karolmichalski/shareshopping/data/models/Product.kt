package pl.karolmichalski.shareshopping.data.models


class Product(val key: String,
			  val name: String,
			  var status: Int) {
	var id: Int = 0
	var isChecked: Boolean = false

	class Status {
		companion object {
			const val ADDED = 0
			const val SYNCED = 1
			const val DELETED = 2
		}
	}
}