package pl.karolmichalski.shoppinglist.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

	val login = MutableLiveData<String>()
	val password = MutableLiveData<String>()
}