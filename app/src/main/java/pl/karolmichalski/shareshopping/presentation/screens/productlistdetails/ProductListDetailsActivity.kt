package pl.karolmichalski.shareshopping.presentation.screens.productlistdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.data.models.Product
import pl.karolmichalski.shareshopping.databinding.ActivityProductlistdetailsBinding
import pl.karolmichalski.shareshopping.presentation.utils.IntentDelegate

class ProductListDetailsActivity : AppCompatActivity(), ProductListDetailsListener {

	companion object {
		private var Intent.listId by IntentDelegate.String("listId")

		fun getIntent(context: Context?, listId: String): Intent {
			return Intent(context, ProductListDetailsActivity::class.java).also {
				it.listId = listId
			}
		}
	}

	private val viewModel by lazy {
		ViewModelProviders.of(this, ProductListDetailsViewModel.Factory(application)).get(ProductListDetailsViewModel::class.java)
	}

	private val showError = Observer<String> {
		Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityProductlistdetailsBinding>(this, R.layout.activity_productlistdetails).also {
			it.setLifecycleOwner(this)
			it.viewModel = viewModel
			it.listener = this
		}
		viewModel.getProducts(intent.listId)
	}

	override fun onProductClick(): (Product) -> Unit {
		return {

		}
	}

	override fun onAddBtnClick() {
		viewModel.addProduct(intent.listId)
	}

}

