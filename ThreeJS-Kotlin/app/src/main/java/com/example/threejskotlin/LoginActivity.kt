package com.example.threejskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.threejskotlin.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        var editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        btnLogin.setOnClickListener {view ->
            var user = editTextEmail.text.toString()
            var pass = editTextPassword.text.toString()
            if(user.isEmpty() || pass.isEmpty()){
                showMessage(view, "Preencha todos dos campos")
            }else{
                signIn(view,user, pass)
            }

        }
    }

    fun signIn(view: View, email: String, password: String){
        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> {respost ->
            if(respost.isSuccessful){
                var intent = Intent(this, RenderActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)
            }
            else{
                showMessage(view, "Login ou senha invalodo!")
            }
        })
    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}
