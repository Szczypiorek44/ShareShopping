package pl.karolmichalski.shoppinglist.domain.user

import dagger.Component
import pl.karolmichalski.shoppinglist.data.user.UserModule
import pl.karolmichalski.shoppinglist.presentation.screens.login.LoginViewModel
import pl.karolmichalski.shoppinglist.presentation.screens.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class])
interface UserComponent {
	fun inject(loginViewModel: LoginViewModel)

	fun inject(mainViewModel: MainViewModel)
}