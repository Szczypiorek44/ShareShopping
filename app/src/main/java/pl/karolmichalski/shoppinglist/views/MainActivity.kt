package pl.karolmichalski.shoppinglist.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.adapters.ProductAdapter
import pl.karolmichalski.shoppinglist.databinding.ActivityMainBinding
import pl.karolmichalski.shoppinglist.utils.observeOnce
import pl.karolmichalski.shoppinglist.viewlisteners.MainListener
import pl.karolmichalski.shoppinglist.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), MainListener {

	private val adapter by lazy {
		ProductAdapter(this).apply {
			recyclerView.adapter = this
		}
	}

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
		viewModel.getProducts().observeOnce(Observer {
			adapter.setProductList(it)
		})
	}

	override fun onAddBtnClick() {
		val product = viewModel.getCreatedProduct()
		viewModel.clearNewProductName()
		adapter.addProduct(product)
	}
}