package pl.karolmichalski.shareshopping.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

	val drawerOpened = MutableLiveData<Boolean>().apply { value = false }

	fun logOut() {

	}
}