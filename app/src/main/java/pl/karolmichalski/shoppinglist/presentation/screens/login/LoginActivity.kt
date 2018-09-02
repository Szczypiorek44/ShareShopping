package pl.karolmichalski.shoppinglist.presentation.screens.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.databinding.ActivityLoginBinding
import pl.karolmichalski.shoppinglist.presentation.screens.main.MainActivity


class LoginActivity : AppCompatActivity(), LoginListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this, LoginViewModel.Factory(application)).get(LoginViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply {
			setLifecycleOwner(this@LoginActivity)
			listener = this@LoginActivity
			viewModel = this@LoginActivity.viewModel
		}
		viewModel.loginSuccess.observe(this@LoginActivity, onLoginSuccess)
		viewModel.errorMessage.observe(this@LoginActivity, showError)
	}

	override fun onLoginBtnClick() {
		viewModel.signInWithEmailAndPassword()
	}


	override fun onRegisterBtnClick() {
		viewModel.createUserWithEmailAndPassword()
	}

	private val onLoginSuccess = Observer<Boolean> {
		startActivity(Intent(this, MainActivity::class.java))
		finish()
	}

	private val showError = Observer<String> {
		Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
	}
}