package pl.karolmichalski.shoppinglist.presentation.utils

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class BundleDelegate<T>(protected val key: String) : ReadWriteProperty<Bundle, T> {

	class Set<K>(key: String) : BundleDelegate<kotlin.collections.Set<K>>(key) {

		override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.collections.Set<K>) {
			thisRef.getInt(key)
		}

		override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.collections.Set<K> {
			return (thisRef.getSerializable(key) as HashSet<*>).map { it as K }.toSet()
		}

	}
}