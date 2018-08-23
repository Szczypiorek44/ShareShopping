package pl.karolmichalski.shoppinglist.presentation.utils

import android.os.Bundle

private const val SELECTED_PRODUCTS = "selected_products"

fun Bundle.addSelectedProducts(selectedProducts: HashSet<String>) {
	putSerializable(SELECTED_PRODUCTS, selectedProducts)
}

fun Bundle.getSelectedProducts(): Set<String> {
	return (getSerializable(SELECTED_PRODUCTS) as HashSet<*>).map { it as String }.toSet()
}