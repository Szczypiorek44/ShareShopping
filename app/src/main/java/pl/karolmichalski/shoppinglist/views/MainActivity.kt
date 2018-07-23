package pl.karolmichalski.shoppinglist.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.databinding.ActivityMainBinding
import pl.karolmichalski.shoppinglist.models.Product
import pl.karolmichalski.shoppinglist.viewlisteners.MainListener
import pl.karolmichalski.shoppinglist.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), MainListener {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

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

    override fun onProductClick(product: Product) {
        viewModel.removeProduct(product)
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

}