package pl.karolmichalski.shoppinglist.domain

import dagger.Component
import pl.karolmichalski.shoppinglist.data.user.UserModule
import pl.karolmichalski.shoppinglist.presentation.screens.login.LoginViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class])
interface UserComponent {
	fun inject(loginViewModel: LoginViewModel)
}