package pl.karolmichalski.shoppinglist.presentation.utils

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class BundleDelegate<T>(protected val key: String) : ReadWriteProperty<Bundle, T> {

	class HashSet<K>(key: String) : BundleDelegate<kotlin.collections.HashSet<K>>(key) {

		override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.collections.HashSet<K>) {
			thisRef.putSerializable(key, value)
		}

		override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.collections.HashSet<K> {
			return (thisRef.getSerializable(key) as kotlin.collections.HashSet<K>)
		}
	}
}