package pl.karolmichalski.shareshopping.presentation

import android.app.Application
import pl.karolmichalski.shareshopping.data.product.ProductModule
import pl.karolmichalski.shareshopping.data.user.UserModule
import pl.karolmichalski.shareshopping.di.AppComponent
import pl.karolmichalski.shareshopping.di.DaggerAppComponent

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent.builder()
				.userModule(UserModule())
				.productModule(ProductModule(applicationContext))
				.build()
	}

}