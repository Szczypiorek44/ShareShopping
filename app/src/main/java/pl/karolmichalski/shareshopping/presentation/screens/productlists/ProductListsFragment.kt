package pl.karolmichalski.shareshopping.presentation.screens.productlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.data.models.ProductList
import pl.karolmichalski.shareshopping.databinding.FragmentProductlistsBinding
import pl.karolmichalski.shareshopping.presentation.dialogs.ListCreationDialog
import pl.karolmichalski.shareshopping.presentation.screens.productlistdetails.ProductListDetailsActivity

class ProductListsFragment : Fragment(), ProductListsListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this, ProductListsViewModel.Factory(activity)).get(ProductListsViewModel::class.java)
	}

	private val showError = Observer<String> {
		Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return DataBindingUtil.inflate<FragmentProductlistsBinding>(inflater, R.layout.fragment_productlists, container, false).also {
			it.setLifecycleOwner(this)
			it.viewModel = viewModel
			it.listener = this
		}.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		viewModel.errorMessage.observe(this, showError)
		activity?.title = getString(R.string.shopping_lists)
		viewModel.getProductLists()
	}

	override fun onItemClick(): (ProductList) -> Unit {
		return {
			val intent = ProductListDetailsActivity.getIntent(context, it.id)
			startActivityForResult(intent, 200)
		}
	}

	override fun onAddClick(view: View) {
		showLogoutDecisionDialog()
	}

	private fun showLogoutDecisionDialog() {
		ListCreationDialog().apply {
			onAcceptClick = { listName ->
				viewModel.createList(listName)
				dismiss()
			}
		}.show(childFragmentManager, ListCreationDialog::javaClass.name)

	}
}