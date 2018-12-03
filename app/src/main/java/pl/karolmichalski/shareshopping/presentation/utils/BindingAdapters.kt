package pl.karolmichalski.shareshopping.presentation.utils

import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.drawerlayout.widget.DrawerLayout

@BindingAdapter("opened")
fun DrawerLayout.setOpened(isOpened: Boolean) {
	if (isOpened)
		openDrawer(GravityCompat.START)
	else
		closeDrawer(GravityCompat.START)
}

@InverseBindingAdapter(attribute = "opened")
fun DrawerLayout.getOpened(): Boolean {
	return isDrawerOpen(GravityCompat.START)
}

@BindingAdapter("app:openedAttrChanged")
fun DrawerLayout.setOpenedListener(attrChange: InverseBindingListener) {
	addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
		override fun onDrawerClosed(p0: View) {
			attrChange.onChange()
		}

		override fun onDrawerOpened(p0: View) {
			attrChange.onChange()
		}
	})
}