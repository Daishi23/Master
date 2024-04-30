package chu.studio

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import chu.studio.ui.theme.MyComposeApplicationTheme
import androidx.compose.ui.unit.dp

import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember

class Webon : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Banner(id = R.string.banner_3)
                        }
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val context = LocalContext.current
                            val urlRazbor = "https://yandex.ru/maps/213/moscow/?ll=37.723134%2C55.753511&mode=usermaps&source=constructorLink&um=constructor%3A0ac7bfb37aa466eb443051a803cf3224f95bff4c60c80e5ffa09d9e73fd8885a&z=10"
                            MyButton("Разборки", urlRazbor, context)
                            val urlZip = "https://yandex.ru/maps/213/moscow/?ll=37.793906%2C55.742804&mode=usermaps&source=constructorLink&um=constructor%3Af07da4a6c04f21eb2c8105c9b68e20ad26857015f9f145dad99999d0c7bc282b&z=9"
                            MyButton("ЗИП", urlZip, context)
                            val urlRFZip = "https://yandex.ru/maps/?um=constructor%3A5a53d87de166fe970c1146081ccfb97007c1b9952b9dfca2abcb13a37c990fa7&source=constructorLink"
                            MyButton("РФ ЗИП и разборки", urlRFZip, context)
                            val urlSales = "https://yandex.ru/maps/?um=constructor%3A28fa58f6e3edb8406231515b1af3d89230a9f3cee4dda3bcc2358949524bceaf&source=constructorLink"
                            MyButton("Скидки, Акции", urlSales, context)
                            val urlASC ="https://yandex.ru/maps/?um=constructor%3A5616611ef28b850dd535b79bef583b9288ca14804d861ef72606bfe6c54c6048&source=constructorLink"
                            MyButton("АСЦ", urlASC, context)
                            val urlSPB= "https://yandex.ru/maps/?um=constructor%3A74d5be3b1b6af38dc0764d74a682db8a7aeded67968781a8555b262d0550297c&source=constructorLink"
                            MyButton("СПБ", urlSPB, context)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyButton(text: String, url: String, context: android.content.Context) {
    Button(
        onClick = {
            if (url.isNotEmpty()) {
                val intent = Intent(context, Web::class.java)
                intent.putExtra("URL", url)
                context.startActivity(intent)
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text)
    }
}