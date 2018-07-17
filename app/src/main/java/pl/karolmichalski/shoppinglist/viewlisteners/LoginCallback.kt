package pl.karolmichalski.shoppinglist.viewlisteners

interface LoginCallback {
	fun onSuccess()

	fun onError(message: String)
}