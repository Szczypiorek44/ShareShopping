package pl.karolmichalski.shareshopping.presentation.utils

import android.content.Intent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class IntentDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<Intent, T> {

	class String(key: kotlin.String) : IntentDelegate<kotlin.String>(key) {
		override fun setValue(thisRef: Intent, property: KProperty<*>, value: kotlin.String) {
			thisRef.putExtra(key, value)
		}

		override fun getValue(thisRef: Intent, property: KProperty<*>): kotlin.String {
			return thisRef.getStringExtra(key)
		}
	}

}