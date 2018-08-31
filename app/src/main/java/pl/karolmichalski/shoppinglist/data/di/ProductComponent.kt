package pl.karolmichalski.shoppinglist.data.di

import dagger.Component
import pl.karolmichalski.shoppinglist.presentation.screens.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductModule::class])
interface ProductComponent {
	fun inject(mainViewModel: MainViewModel)
}