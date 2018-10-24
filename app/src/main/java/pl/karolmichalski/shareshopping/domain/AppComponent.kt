package pl.karolmichalski.shareshopping.domain

import dagger.Component
import pl.karolmichalski.shareshopping.data.product.ProductModule
import pl.karolmichalski.shareshopping.data.user.UserModule
import pl.karolmichalski.shareshopping.presentation.screens.login.LoginViewModel
import pl.karolmichalski.shareshopping.presentation.screens.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class, ProductModule::class])
interface AppComponent {
	fun inject(loginViewModel: LoginViewModel)

	fun inject(mainViewModel: MainViewModel)
}