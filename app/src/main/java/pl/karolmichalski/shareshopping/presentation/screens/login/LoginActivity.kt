package pl.karolmichalski.shareshopping.presentation.screens.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.databinding.ActivityLoginBinding
import pl.karolmichalski.shareshopping.presentation.screens.main.MainActivity
import pl.karolmichalski.shareshopping.presentation.screens.register.RegisterActivity


class LoginActivity : AppCompatActivity(), LoginListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this, LoginViewModel.Factory(application)).get(LoginViewModel::class.java)
	}

	private val onLoginSuccess = Observer<Boolean> {
		showMainActivity()
	}

	private val showError = Observer<String> {
		Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (viewModel.isUserLogged())
			showMainActivity()
		else
			init()
	}

	private fun init() {
		DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply {
			setLifecycleOwner(this@LoginActivity)
			listener = this@LoginActivity
			viewModel = this@LoginActivity.viewModel
		}
		viewModel.loginSuccess.observe(this@LoginActivity, onLoginSuccess)
		viewModel.errorMessage.observe(this@LoginActivity, showError)
	}

	private fun showMainActivity() {
		startActivity(Intent(this, MainActivity::class.java))
		finish()
	}

	override fun onLoginBtnClick() {
		viewModel.logIn()
	}

	override fun onRegisterBtnClick() {
		startActivity(Intent(this, RegisterActivity::class.java))
	}

}