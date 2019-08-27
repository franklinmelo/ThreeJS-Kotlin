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

        val btnRenderCube = findViewById<Button>(R.id.buttonRenderCube)

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



        btnRenderCube.setOnClickListener {webView.loadUrl("file:///android_asset/cube.html")}

        btnRenderText.setOnClickListener {

            val text = editTextRender.text.toString()
            val html: String = "<html>\n" +
                    "    <head>\n" +
                    "        <title>My first three.js app</title>\n" +
                    "        <style>\n" +
                    "            body {\n" +
                    "                margin: 0;\n" +
                    "            }\n" +
                    "        canvas {\n" +
                    "            width: 100%;\n" +
                    "            height: 100%;\n" +
                    "        }\n" +
                    "        </style>\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <script src=\"https://threejs.org/build/three.js\"></script>\n" +
                    "        <script>\n" +
                    "            var scene = new THREE.Scene();\n" +
                    "            var camera = new THREE.PerspectiveCamera(\n" +
                    "        75,\n" +
                    "        window.innerWidth / window.innerHeight,\n" +
                    "        0.1,\n" +
                    "        1000\n" +
                    "        );\n" +
                    "\n" +
                    "        var renderer = new THREE.WebGLRenderer({ alpha: true }  );\n" +
                    "        renderer.setSize( window.innerWidth, window.innerHeight );\n" +
                    "        renderer.setClearColor( 0xffffff, 0 );\n" +
                    "        document.body.appendChild( renderer.domElement );\n" +
                    "\n" +
                    "        var loader = new THREE.FontLoader();\n" +
                    "\n" +
                    "        loader.load( 'https://cdn.rawgit.com/mrdoob/three.js/master/examples/fonts/helvetiker_regular.typeface.json', function ( font ) {\n" +
                    "             \n" +
                    "             var geometry = new THREE.TextGeometry( '$text', {\n" +
                    "                                                   font: font,\n" +
                    "                                                   size: 4,\n" +
                    "                                                   height: 0.3,\n" +
                    "                                                   curveSegments: 10,\n" +
                    "                                                   bevelSize: 0,\n" +
                    "                                                   bevelOffset: 0,\n" +
                    "                                                   bevelSegments: 0\n" +
                    "                                                   } );\n" +
                    "             \n" +
                    "             var material = new THREE.MeshBasicMaterial({ color: 0xff0000 });\n" +
                    "             var cube = new THREE.Mesh(geometry, material);\n" +
                    "             scene.add(cube);\n" +
                    "             \n" +
                    "             camera.position.z = 30;\n" +
                    "             camera.position.x = 30;\n" +
                    "             camera.position.y = 1;\n" +
                    "             \n" +
                    "             var animate = function() {\n" +
                    "             requestAnimationFrame(animate);\n" +
                    "             renderer.render(scene, camera);\n" +
                    "             };\n" +
                    "             \n" +
                    "             animate();\n" +
                    "             } );\n" +
                    "                                                                 \n" +
                    "            </script>\n" +
                    "    </body>\n" +
                    "</html>\n"
            webView.loadData(html, "text/html", "UTF-8");
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
