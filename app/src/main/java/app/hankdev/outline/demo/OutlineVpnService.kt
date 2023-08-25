package app.hankdev.outline.demo

import android.content.Context
import android.content.Intent
import android.net.VpnService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class OutlineVpnService : VpnService() {
    companion object {
        private const val TAG = "OutlineVpnService"
        private const val ACTION_START = "action.start"
        private const val ACTION_STOP = "action.stop"

        fun start(context: Context) {
            context.startService(newIntent(context, ACTION_START))
        }

        fun stop(context: Context) {
            context.startService(newIntent(context, ACTION_STOP))
        }

        private fun newIntent(context: Context, action: String): Intent {
            return Intent(context, OutlineVpnService::class.java).apply {
                this.action = action
            }
        }
    }

    private var isRunning = false

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        Log.i(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: ")
        val action = intent?.action
        return when {
            action == ACTION_START && !isRunning -> {
                start()
                isRunning = true
                START_STICKY
            }

            action == ACTION_STOP -> {
                stop()
                START_NOT_STICKY
            }

            else -> START_STICKY
        }
    }

    private fun start() = scope.launch(Dispatchers.IO) {

    }

    private fun stop() {
        stopSelf()
    }

    override fun onRevoke() {
        super.onRevoke()
        Log.i(TAG, "onRevoke: ")
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: ")
    }
}