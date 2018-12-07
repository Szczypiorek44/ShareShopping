package pl.karolmichalski.shareshopping.presentation.screens.productlistdetails

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class ProductListDetailsActivity : AppCompatActivity() {

	private val viewModel by lazy {
		ViewModelProviders.of(this, ProductListDetailsViewModel.Factory(application)).get(ProductListDetailsViewModel::class.java)
	}

	private val showError = Observer<String> {
		Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}


}