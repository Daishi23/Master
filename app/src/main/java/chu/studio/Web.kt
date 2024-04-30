package chu.studio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebSettings

class Web : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("URL") ?: "https://www.yandex.ru"

        setContent {
            WebViewContent(url = url)
        }
    }

    @Composable
    fun WebViewContent(url: String) {
        AndroidView(factory = { ctx ->
            WebView(ctx).apply {
                val settings = settings
                settings.javaScriptEnabled = true
                
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        if (url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                            return true
                        }
                        if (url.contains("popup=true")) {
                            view.loadUrl(url)
                            return true
                        }
                        return super.shouldOverrideUrlLoading(view, url)
                    }
                }
                loadUrl(url)
            }
        }, modifier = Modifier.fillMaxSize())
    }
}