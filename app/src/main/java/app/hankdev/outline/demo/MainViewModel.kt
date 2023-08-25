package app.hankdev.outline.demo

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun startVpn() {
        println("start VPN")
    }

    fun stopVpn() {
        println("stop VPN")
    }
}