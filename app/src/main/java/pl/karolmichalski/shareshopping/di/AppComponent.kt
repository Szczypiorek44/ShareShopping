package pl.karolmichalski.shareshopping.di

import dagger.Component
import pl.karolmichalski.shareshopping.data.product.ProductModule
import pl.karolmichalski.shareshopping.data.user.UserModule
import pl.karolmichalski.shareshopping.presentation.screens.login.LoginViewModel
import pl.karolmichalski.shareshopping.presentation.screens.main.MainViewModel
import pl.karolmichalski.shareshopping.presentation.screens.register.RegisterViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, UserModule::class, ProductModule::class])
interface AppComponent {
	fun inject(registerViewModel: RegisterViewModel)
	fun inject(loginViewModel: LoginViewModel)
	fun inject(mainViewModel: MainViewModel)
}