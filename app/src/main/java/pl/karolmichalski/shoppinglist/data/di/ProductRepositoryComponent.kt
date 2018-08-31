package pl.karolmichalski.shoppinglist.data.di

import dagger.Component
import pl.karolmichalski.shoppinglist.presentation.screens.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductRepositoryModule::class])
interface ProductRepositoryComponent {
	fun inject(mainViewModel: MainViewModel)
}