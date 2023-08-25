package app.hankdev.outline.demo

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun connect() {
        println("connect")
    }

    fun disconnect() {
        println("disconnect")
    }
}