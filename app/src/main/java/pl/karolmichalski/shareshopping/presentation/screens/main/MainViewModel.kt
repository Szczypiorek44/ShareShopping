package pl.karolmichalski.shareshopping.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.karolmichalski.shareshopping.data.SharedPrefs
import javax.inject.Inject

class MainViewModel : ViewModel() {

	val drawerOpened = MutableLiveData<Boolean>().apply { value = false }

	@Inject
	lateinit var sharedPrefs: SharedPrefs

	fun logOut() {
		sharedPrefs.saveUid("")
	}
}