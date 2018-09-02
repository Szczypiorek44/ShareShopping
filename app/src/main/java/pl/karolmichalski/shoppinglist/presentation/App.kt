package pl.karolmichalski.shoppinglist.presentation

import android.app.Application
import pl.karolmichalski.shoppinglist.data.product.ProductModule
import pl.karolmichalski.shoppinglist.data.user.UserModule
import pl.karolmichalski.shoppinglist.domain.AppComponent
import pl.karolmichalski.shoppinglist.domain.DaggerAppComponent

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent.builder()
				.userModule(UserModule())
				.productModule(ProductModule(applicationContext))
				.build()
	}

}