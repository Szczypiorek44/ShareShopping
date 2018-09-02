package pl.karolmichalski.shoppinglist.presentation

import android.app.Application
import pl.karolmichalski.shoppinglist.data.product.ProductModule
import pl.karolmichalski.shoppinglist.domain.DaggerProductComponent
import pl.karolmichalski.shoppinglist.domain.DaggerUserComponent
import pl.karolmichalski.shoppinglist.domain.ProductComponent
import pl.karolmichalski.shoppinglist.domain.UserComponent

class App : Application() {

	val productComponent: ProductComponent by lazy {
		DaggerProductComponent.builder()
				.productModule(ProductModule(applicationContext))
				.build()
	}

	val userComponent: UserComponent by lazy {
		DaggerUserComponent.create()
	}

}