package pl.karolmichalski.shareshopping.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.databinding.DialogListcreationBinding

class ListCreationDialog : DialogFragment() {

	var onAcceptClick: (String?) -> Unit = {}

	private val listName = MutableLiveData<String>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return DataBindingUtil.inflate<DialogListcreationBinding>(inflater, R.layout.dialog_listcreation, container, false).also {
			it.setLifecycleOwner(this)
			it.onAcceptClick = {
				onAcceptClick(listName.value)
			}
			it.listName = listName
		}.root
	}
}