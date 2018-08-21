package pl.karolmichalski.shoppinglist.presentation

import android.app.Application
import pl.karolmichalski.shoppinglist.data.ProductsRepositoryImpl
import pl.karolmichalski.shoppinglist.domain.ProductsRepository

class App : Application() {

	val productsRepository: ProductsRepository by lazy { ProductsRepositoryImpl(this) }

}