package app.hankdev.outline.demo

import android.content.Context
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun startVpn(context: Context) {
        OutlineVpnService.start(context)
    }

    fun stopVpn(context: Context) {
        OutlineVpnService.stop(context)
    }
}