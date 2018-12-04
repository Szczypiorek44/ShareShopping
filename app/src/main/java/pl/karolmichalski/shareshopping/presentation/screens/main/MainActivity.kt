package pl.karolmichalski.shareshopping.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.databinding.ActivityMainBinding
import pl.karolmichalski.shareshopping.presentation.dialogs.DecisionDialog
import pl.karolmichalski.shareshopping.presentation.screens.login.LoginActivity
import pl.karolmichalski.shareshopping.presentation.screens.productlists.ProductListsFragment

class MainActivity : AppCompatActivity(), MainListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this).get(MainViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
			setLifecycleOwner(this@MainActivity)
			viewModel = this@MainActivity.viewModel
			listener = this@MainActivity
		}
		supportActionBar?.apply {
			setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px)
			setDisplayHomeAsUpEnabled(true)
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
		R.id.logout -> {
			showLogoutDecisionDialog()
			true
		}
		android.R.id.home -> {
			viewModel.drawerOpened.value = viewModel.drawerOpened.value?.not()
			true
		}
		else -> super.onOptionsItemSelected(item)
	}

	override fun onShoppingListsClick() {
		viewModel.drawerOpened.value = false
		showFragment(ProductListsFragment())
	}

	private fun showLogoutDecisionDialog() {
		val dialog = DecisionDialog()
		dialog.title = getString(R.string.are_you_sure_you_want_to_log_out_question)
		dialog.onButton1Click = { logout() }
		dialog.onButton2Click = { dialog.dismiss() }
		dialog.show(supportFragmentManager, dialog.javaClass.simpleName)
	}

	private fun showFragment(fragment: Fragment) {
		val fragmentTransaction = supportFragmentManager.beginTransaction()
		fragmentTransaction
				.replace(R.id.fragmentContainer, fragment)
				.commit()
	}

	private fun logout() {
		viewModel.logOut()
		startActivity(Intent(this, LoginActivity::class.java))
		finish()
	}


}