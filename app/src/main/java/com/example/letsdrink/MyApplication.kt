package com.example.letsdrink

import android.app.Application
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat

fun Application.isOnline(): Boolean {
        val debug_tag= "NET_TAG"
        val connMgr =
            ContextCompat.getSystemService(applicationContext, ConnectivityManager::class.java)
        var isWifiConn: Boolean = false
        var isMobileConn: Boolean = false
        connMgr!!.allNetworks.forEach { network ->
            connMgr.getNetworkInfo(network).apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or isConnected
                }
                if (type == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = isMobileConn or isConnected
                }
            }
        }
        Log.d(debug_tag, "Wifi connected: $isWifiConn")
        Log.d(debug_tag, "Mobile connected: $isMobileConn")
        return isWifiConn && isMobileConn
    }
