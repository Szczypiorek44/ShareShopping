package pl.karolmichalski.shoppinglist.views

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.databinding.ActivityLoginBinding
import pl.karolmichalski.shoppinglist.viewlisteners.LoginListener
import pl.karolmichalski.shoppinglist.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this).get(LoginViewModel::class.java).apply {
			loginSuccess.observe(this@LoginActivity, onLoginSuccess)
			errorMessage.observe(this@LoginActivity, showError)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply {
			setLifecycleOwner(this@LoginActivity)
			listener = this@LoginActivity
			viewModel = this@LoginActivity.viewModel
		}
	}

	override fun onLoginBtnClick() {
		viewModel.signInWithEmailAndPassword()
	}


	override fun onRegisterBtnClick() {
		viewModel.createUserWithEmailAndPassword()
	}

	private val onLoginSuccess = Observer<Boolean> {
		setResult(Activity.RESULT_OK)
		finish()
	}

	private val showError = Observer<String> {
		Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
	}
}