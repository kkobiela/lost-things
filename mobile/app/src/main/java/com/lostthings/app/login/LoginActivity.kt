package com.lostthings.app.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.lostthings.app.base.BaseActivity
import com.lostthings.app.items.ItemsActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lostthings.R.layout.activity_login)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        loginGoogleSignInBtn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, REQUEST_CODE)
        }
    }

    public override fun onStart() {
        super.onStart()
        onLoggedIn(GoogleSignIn.getLastSignedInAccount(this) ?: return)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                101 -> try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val account = task.getResult(ApiException::class.java) ?: return
                    onLoggedIn(account)
                } catch (e: ApiException) {
                }
            }
    }

    private fun onLoggedIn(googleSignInAccount: GoogleSignInAccount) {
        with(googleSignInAccount) {
            repository.profilePhotoUrl = photoUrl.toString()
            repository.profileName = displayName ?: ""
            repository.profileEmail = email ?: ""
        }
        startActivity(Intent(this, ItemsActivity::class.java))
        finish()
    }

    companion object {
        private const val REQUEST_CODE = 101
    }
}
