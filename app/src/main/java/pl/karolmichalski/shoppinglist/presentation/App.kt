package pl.karolmichalski.shoppinglist.presentation

import android.app.Application
import pl.karolmichalski.shoppinglist.data.di.DaggerProductComponent
import pl.karolmichalski.shoppinglist.data.di.ProductComponent
import pl.karolmichalski.shoppinglist.data.di.ProductModule

class App : Application() {

	val productComponent: ProductComponent by lazy {
		DaggerProductComponent.builder()
				.productModule(ProductModule(applicationContext))
				.build()
	}

}