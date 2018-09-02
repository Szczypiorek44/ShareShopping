package pl.karolmichalski.shoppinglist.presentation

import android.app.Application
import pl.karolmichalski.shoppinglist.data.product.ProductModule
import pl.karolmichalski.shoppinglist.domain.product.DaggerProductComponent
import pl.karolmichalski.shoppinglist.domain.product.ProductComponent
import pl.karolmichalski.shoppinglist.domain.user.DaggerUserComponent
import pl.karolmichalski.shoppinglist.domain.user.UserComponent

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