package pl.karolmichalski.shareshopping.di

import dagger.Component
import pl.karolmichalski.shareshopping.presentation.screens.register.RegisterViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface AppComponent {
	fun inject(registerViewModel: RegisterViewModel)
}