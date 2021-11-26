package com.example.deprem.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.example.deprem.view.MainActivity

class ScreenActionReceiver : BroadcastReceiver() {
    private val TAG = "ScreenActionReceiver"
    override fun onReceive(context: Context, intent: Intent) {


        //LOG
        val sb = StringBuilder()
        sb.append(
            """
                Action: ${intent.action}
                
                """.trimIndent()
        )
        sb.append(
            """
                URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}
                
                """.trimIndent()
        )
        val log = sb.toString()
        Log.d(TAG, log)
        Toast.makeText(context, log, Toast.LENGTH_LONG).show()
        val action = intent.action
        if (Intent.ACTION_SCREEN_ON == action) {
            Log.d(TAG, "screen is on...")
            Toast.makeText(context, "screen ON", Toast.LENGTH_LONG)

            //Run the locker
            context.startService(Intent(context, MainActivity::class.java))
        } else if (Intent.ACTION_SCREEN_OFF == action) {
            Log.d(TAG, "screen is off...")
            Toast.makeText(context, "screen OFF", Toast.LENGTH_LONG)
        } else if (Intent.ACTION_USER_PRESENT == action) {
            Log.d(TAG, "screen is unlock...")
            Toast.makeText(context, "screen UNLOCK", Toast.LENGTH_LONG)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(Intent(context, MainActivity::class.java))
            } else {
                context.startService(Intent(context, MainActivity::class.java))
            }
        } else if (Intent.ACTION_BOOT_COMPLETED == action) {
            Log.d(TAG, "boot completed...")
            Toast.makeText(context, "BOOTED..", Toast.LENGTH_LONG)
            //Run the locker
/*            Intent i = new Intent(context, FloatingWindow.class);
            context.startService(i);

*/if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(Intent(context, MainActivity::class.java))
            } else {
                context.startService(Intent(context, MainActivity::class.java))
            }
        }
    }

    val filter: IntentFilter
        get() {
            val filter = IntentFilter()
            filter.addAction(Intent.ACTION_SCREEN_OFF)
            filter.addAction(Intent.ACTION_SCREEN_ON)
            return filter
        }
}