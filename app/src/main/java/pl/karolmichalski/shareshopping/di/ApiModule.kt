package pl.karolmichalski.shareshopping.di

import android.content.Context
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.karolmichalski.shareshopping.BuildConfig
import pl.karolmichalski.shareshopping.data.ApiInterface
import pl.karolmichalski.shareshopping.data.ApiRepositoryImpl
import pl.karolmichalski.shareshopping.data.SharedPrefs
import pl.karolmichalski.shareshopping.data.SharedPrefsImpl
import pl.karolmichalski.shareshopping.domain.ApiRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

private const val API_URL = "http://shoppingapipz.azurewebsites.net/ShoppingAPI.svc/JsonShoppingAPI/"

@Module
class ApiModule(private val context: Context) {

	@Provides
	@Singleton
	fun provideApiService(): ApiInterface {
		val loggingInterceptor = HttpLoggingInterceptor().apply {
			level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
		}

		val okHttpClient = OkHttpClient.Builder()
				.addInterceptor(loggingInterceptor)
				.build()

		val objectMapper = ObjectMapper()
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

		val retrofit = Retrofit.Builder()
				.baseUrl(API_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okHttpClient)
				.addConverterFactory(JacksonConverterFactory.create(objectMapper))
				.build()

		return retrofit.create(ApiInterface::class.java)
	}

	@Provides
	@Singleton
	fun provideSharedPrefs(): SharedPrefs = SharedPrefsImpl(context)

	@Provides
	@Singleton
	fun provideApiRepository(apiInterface: ApiInterface, sharedPrefs: SharedPrefs): ApiRepository = ApiRepositoryImpl(context, apiInterface, sharedPrefs)

}