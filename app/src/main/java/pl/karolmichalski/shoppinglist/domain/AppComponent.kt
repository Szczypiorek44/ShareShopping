package pl.karolmichalski.shoppinglist.domain

import dagger.Component
import pl.karolmichalski.shoppinglist.data.product.ProductModule
import pl.karolmichalski.shoppinglist.data.user.UserModule
import pl.karolmichalski.shoppinglist.presentation.screens.login.LoginViewModel
import pl.karolmichalski.shoppinglist.presentation.screens.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class, ProductModule::class])
interface AppComponent {
	fun inject(loginViewModel: LoginViewModel)

	fun inject(mainViewModel: MainViewModel)
}