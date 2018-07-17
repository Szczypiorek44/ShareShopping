package pl.karolmichalski.shoppinglist.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.databinding.ActivityLoginBinding
import pl.karolmichalski.shoppinglist.viewlisteners.LoginCallback
import pl.karolmichalski.shoppinglist.viewlisteners.LoginListener
import pl.karolmichalski.shoppinglist.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this).get(LoginViewModel::class.java)
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
		viewModel.signInWithEmailAndPassword(loginCallback)
	}


	override fun onRegisterBtnClick() {
		viewModel.createUserWithEmailAndPassword(loginCallback)
	}

	private val loginCallback = object : LoginCallback {
		override fun onSuccess() {
			setResult(RESULT_OK)
			finish()
		}

		override fun onError(message: String) {
			Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
		}

	}
}