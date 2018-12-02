package pl.karolmichalski.shareshopping.presentation

import android.app.Application
import pl.karolmichalski.shareshopping.di.ApiModule
import pl.karolmichalski.shareshopping.di.AppComponent
import pl.karolmichalski.shareshopping.di.DaggerAppComponent

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent.builder()
				.apiModule(ApiModule(applicationContext))
				.build()
	}

}