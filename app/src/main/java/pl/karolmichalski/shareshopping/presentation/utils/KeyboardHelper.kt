package pl.karolmichalski.shareshopping.presentation.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun Activity.hideSoftKeyboard() {
	val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
	inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}
