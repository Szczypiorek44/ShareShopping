package pl.karolmichalski.shoppinglist.presentation

import android.app.Application
import pl.karolmichalski.shoppinglist.data.di.DaggerProductRepositoryComponent
import pl.karolmichalski.shoppinglist.data.di.ProductRepositoryComponent
import pl.karolmichalski.shoppinglist.data.di.ProductRepositoryModule

class App : Application() {

	val productRepositoryComponent: ProductRepositoryComponent by lazy {
		DaggerProductRepositoryComponent.builder().productRepositoryModule(ProductRepositoryModule(applicationContext)).build()
	}

}