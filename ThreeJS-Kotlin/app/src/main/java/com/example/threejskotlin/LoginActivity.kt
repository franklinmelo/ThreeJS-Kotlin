package com.example.threejskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE

        btnLogin.setOnClickListener {view ->
            val user = editTextEmail.text.toString()
            val pass = editTextPassword.text.toString()
            if(user.isEmpty() || pass.isEmpty()){
                showMessage(view, "Preencha todos dos campos")
            }else{
                progressBar.visibility = View.VISIBLE
                signIn(view,user, pass, progressBar)
            }

        }
    }

    fun signIn(view: View, email: String, password: String, progressBar: ProgressBar){
        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> {respost ->
            if(respost.isSuccessful){
                val intent = Intent(this, RenderActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)
                progressBar.visibility = View.GONE
            }
            else{
                progressBar.visibility = View.GONE
                showMessage(view, "Login ou senha invalodo!")
            }
        })
    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}
