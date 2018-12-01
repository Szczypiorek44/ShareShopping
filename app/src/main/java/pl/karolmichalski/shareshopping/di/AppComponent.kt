package pl.karolmichalski.shareshopping.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface AppComponent {
	fun inject()
}