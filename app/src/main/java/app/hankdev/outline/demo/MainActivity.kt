package app.hankdev.outline.demo

import android.net.VpnService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.hankdev.outline.demo.ui.theme.Outlinegotun2socksdemoTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val vpnPreparation = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result -> if (result.resultCode == RESULT_OK) viewModel.startVpn(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Outlinegotun2socksdemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val modifier = Modifier.fillMaxWidth()

                    Column(verticalArrangement = Arrangement.Center) {
                        TextButton(name = "Connect", modifier) {
                            startVpn()
                        }
                        TextButton(name = "Disconnect", modifier) {
                            viewModel.stopVpn(this@MainActivity)
                        }
                    }
                }
            }
        }
    }

    private fun startVpn() = VpnService.prepare(this)?.let {
        vpnPreparation.launch(it)
    } ?: viewModel.startVpn(this)
}

@Composable
fun TextButton(name: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun TextButtonPreview() {
    Outlinegotun2socksdemoTheme {
        TextButton(name = "connect")
    }
}