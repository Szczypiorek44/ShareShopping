package pl.karolmichalski.shoppinglist.presentation.utils

import android.support.v7.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import pl.karolmichalski.shoppinglist.R

class ActionModeManager(private val callback: Callback) {

	private var mode: ActionMode? = null
	private var isActive = false

	fun invalidateCount(checkedProductCount: Int) {
		if (checkedProductCount > 0 && !isActive)
			mode = callback.onStartSupportActionMode(actionModeCallback)
		else if (checkedProductCount == 0)
			mode?.finish()
		updateCountTitle(checkedProductCount)
	}

	private fun updateCountTitle(count: Int) {
		mode?.menu?.findItem(R.id.count)?.title = count.toString()
	}

	private val actionModeCallback = object : ActionMode.Callback {
		override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
			mode?.menuInflater?.inflate(R.menu.menu, menu)
			isActive = true
			return true
		}

		override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
			return false
		}

		override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
			if (item?.itemId == R.id.delete) {
				callback.onDeleteButtonClicked()
				mode?.finish()
				return true
			}
			return false
		}

		override fun onDestroyActionMode(mode: ActionMode?) {
			callback.onActionModeDestroyed()
			isActive = false
		}
	}

	interface Callback {
		fun onStartSupportActionMode(callback: ActionMode.Callback): ActionMode?

		fun onDeleteButtonClicked()

		fun onActionModeDestroyed()
	}

}