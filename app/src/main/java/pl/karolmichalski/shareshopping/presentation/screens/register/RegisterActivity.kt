package pl.karolmichalski.shareshopping.presentation.screens.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.databinding.ActivityRegisterBinding
import pl.karolmichalski.shareshopping.presentation.screens.main.MainActivity


class RegisterActivity : AppCompatActivity(), RegisterListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this, RegisterViewModel.Factory(application)).get(RegisterViewModel::class.java)
	}

	private val finishAllAndStartMainActivity = {
		finishAffinity()
		startActivity(Intent(this, MainActivity::class.java))
	}

	private val showError = Observer<String> {
		Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register).also {
			it.setLifecycleOwner(this)
			it.listener = this
			it.viewModel = viewModel
		}
		viewModel.errorMessage.observe(this, showError)
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return true
	}

	override fun onRegisterBtnClick() {
		viewModel.register(onSuccess = finishAllAndStartMainActivity)
	}
}