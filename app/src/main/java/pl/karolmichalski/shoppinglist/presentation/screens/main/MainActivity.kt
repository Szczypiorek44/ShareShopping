package pl.karolmichalski.shoppinglist.presentation.screens.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.data.models.Product
import pl.karolmichalski.shoppinglist.databinding.ActivityMainBinding
import pl.karolmichalski.shoppinglist.presentation.utils.ActionModeManager
import pl.karolmichalski.shoppinglist.presentation.utils.addSelectedProducts
import pl.karolmichalski.shoppinglist.presentation.utils.getSelectedProducts


class MainActivity : AppCompatActivity(), MainListener, ActionModeManager.Callback {

	private val viewModel by lazy {
		ViewModelProviders.of(this, MainViewModel.Factory(application)).get(MainViewModel::class.java)
	}

	private val actionModeManager by lazy {
		ActionModeManager(this)
	}

	companion object {
		fun start(context: Context){
			context.startActivity(Intent(context, MainActivity::class.java))
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
			setLifecycleOwner(this@MainActivity)
			viewModel = this@MainActivity.viewModel
			listener = this@MainActivity
		}
		viewModel.getProducts(this)
	}

	override fun onSaveInstanceState(outState: Bundle?) {
		super.onSaveInstanceState(outState)
		outState?.addSelectedProducts(viewModel.selectedProducts)
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
		super.onRestoreInstanceState(savedInstanceState)
		savedInstanceState?.getSelectedProducts()?.let {
			viewModel.selectedProducts.addAll(it)
		}
	}

	override fun onAddBtnClick() {
		viewModel.productName.value?.let { name ->
			viewModel.addProduct(name)
		}
		viewModel.clearProductName()
	}

	override fun onProductClick(): (Product) -> Unit {
		return {
			viewModel.invalidateProductSelection(it)
			val checkedProductsCount = viewModel.selectedProducts.size
			actionModeManager.invalidateCount(checkedProductsCount)
		}
	}

	override fun onStartSupportActionMode(callback: ActionMode.Callback): ActionMode? {
		return startSupportActionMode(callback)
	}

	override fun onDeleteButtonClicked() {
		viewModel.removeCheckedProducts()
	}

	override fun onActionModeDestroyed() {
		viewModel.deselectAllProducts()
		viewModel.productList.value = viewModel.productList.value
	}
}