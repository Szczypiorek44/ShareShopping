package pl.karolmichalski.shareshopping.di

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.karolmichalski.shareshopping.BuildConfig
import pl.karolmichalski.shareshopping.data.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

private const val API_URL = "http://shoppingapipz.azurewebsites.net/ShoppingAPI.svc/JsonShoppingAPI/"

@Module
class ApiModule {

	@Provides
	@Singleton
	fun provideApiService(): ApiService {
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

		return retrofit.create(ApiService::class.java)
	}
}