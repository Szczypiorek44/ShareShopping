package pl.karolmichalski.shoppinglist.presentation.screens.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.data.models.Product
import pl.karolmichalski.shoppinglist.databinding.ActivityMainBinding
import pl.karolmichalski.shoppinglist.presentation.screens.login.LoginActivity

class MainActivity : AppCompatActivity(), MainListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this, MainViewModel.Factory(application)).get(MainViewModel::class.java)
	}

	private val actionModeManager = ActionModeManager()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (viewModel.isUserLogged())
			init()
		else
			startLoginActivity()
	}

	override fun onAddBtnClick() {
		viewModel.addProduct()
		viewModel.clearProductName()
	}

	override fun onProductClick(): (Product) -> Unit {
		return {
			viewModel.updateProduct(it) //database needs to know that product is checked
			actionModeManager.invalidate()
		}
	}

	private fun init() {
		DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
			setLifecycleOwner(this@MainActivity)
			viewModel = this@MainActivity.viewModel
			listener = this@MainActivity
		}
		viewModel.getProducts(this)
	}

	private fun startLoginActivity() {
		startActivity(Intent(this, LoginActivity::class.java))
		finish()
	}

	inner class ActionModeManager {

		private var mode: ActionMode? = null
		private var menu: Menu? = null

		private var isActive = false

		fun invalidate() {
			val checkedProductCount = viewModel.getCheckedProductsCount()
			if (checkedProductCount > 0 && !isActive)
				startSupportActionMode(actionModeCallback)
			updateCount()
			if (checkedProductCount == 0)
				mode?.finish()
		}

		private fun updateCount() {
			menu?.findItem(R.id.count)?.title = viewModel.getCheckedProductsCount().toString()
		}

		private val actionModeCallback = object : ActionMode.Callback {
			override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
				this@ActionModeManager.mode = mode
				this@ActionModeManager.menu = menu
				this@ActionModeManager.isActive = true
				mode?.menuInflater?.inflate(R.menu.menu, menu)
				return true
			}

			override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
				return false
			}

			override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
				if (item?.itemId == R.id.delete) {
					viewModel.removeCheckedProducts()
					mode?.finish()
					return true
				}
				return false
			}

			override fun onDestroyActionMode(mode: ActionMode?) {
				this@ActionModeManager.mode = null
				this@ActionModeManager.menu = null
				this@ActionModeManager.isActive = false
				viewModel.deselectAllProducts()
				recyclerView.adapter.notifyDataSetChanged()
			}
		}
	}


}