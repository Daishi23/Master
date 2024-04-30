package chu.studio

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
import android.content.Intent
import android.content.Context
import androidx.compose.ui.platform.LocalContext





            

class MainActivity : ComponentActivity() {
     

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
        setContent {
            MyComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Banner(id = R.string.banner_1)
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            // Второй Box с контентом
                           
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        
            Button(
                onClick = {openWebonActivity(this@MainActivity)},
                modifier = Modifier.fillMaxWidth()
            ) {
                // Button content
                Text("Карты ОнЛайн")
            }
            Button(
                onClick = {openWebofActivity(this@MainActivity)},
                modifier = Modifier.fillMaxWidth()
            ) {
                // Button content
                Text("Карты ОффЛайн")
            }
            Button(
                onClick = {openKLActivity(this@MainActivity) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Учет клиентов")
            }
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                // Button content
            }
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                // Button content
            }
        }
    }
}
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            // Третий Box с контентом
                        }
                    }
                }
            }
        }
        
       

    private fun openWebonActivity(context: Context) {
        // Открыть активность Webon
        val intent = Intent(context, Webon::class.java)
        context.startActivity(intent)
    }
   private fun openWebofActivity(context: Context) {
        // Открыть активность Webon
        val intent = Intent(context, Webof::class.java)
        context.startActivity(intent)
    }


   

    