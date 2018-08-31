package pl.karolmichalski.shoppinglist.presentation

import android.app.Application
import pl.karolmichalski.shoppinglist.data.di.ProductRepositoryComponent
import pl.karolmichalski.shoppinglist.data.di.ProductRepositoryModule
import pl.karolmichalski.shoppinglist.presentation.screens.main.DaggerMainComponent

class App : Application() {

	val productRepositoryComponent: ProductRepositoryComponent by lazy {
		DaggerMainComponent.builder().mainModule(ProductRepositoryModule(applicationContext)).build()
	}

}