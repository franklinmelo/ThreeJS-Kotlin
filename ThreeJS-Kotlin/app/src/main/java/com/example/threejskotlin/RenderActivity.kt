package com.example.threejskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import com.example.threejskotlin.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class RenderActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_render)

        val webView = findViewById<WebView>(R.id.webview)

        val btnLogOut = findViewById<Button>(R.id.btnLogout)

        val btnRenderText = findViewById<Button>(R.id.buttonRenderText)

        val editTextRender = findViewById<EditText>(R.id.editTextRender)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.setSupportZoom(true)
        webSettings.defaultTextEncodingName = "utf-8"
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("file:///android_asset/cube.html")

        btnRenderText.setOnClickListener {

            val text = editTextRender.text.toString()

            webView.loadUrl("javascript:renderText('$text')");
        }

        btnLogOut.setOnClickListener{ view ->
            showMessage(view, "Logging Out...")
            signOut()
        }

        fbAuth.addAuthStateListener {
            if(fbAuth.currentUser == null){
                this.finish()
            }
        }
    }

    fun signOut(){
        fbAuth.signOut()

    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}
