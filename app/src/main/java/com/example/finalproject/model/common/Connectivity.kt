package com.example.finalproject.model.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

class Connectivity(private val context: Context) {

    private val connectivityManager:ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    init{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(
                object:ConnectivityManager.NetworkCallback(){
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        isConnected = true
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        isConnected=true
                    }

                    override fun onCapabilitiesChanged(
                        network: Network,
                        networkCapabilities: NetworkCapabilities
                    ) {
                        super.onCapabilitiesChanged(network, networkCapabilities)
                    }

                    override fun onLinkPropertiesChanged(
                        network: Network,
                        linkProperties: LinkProperties
                    ) {
                        super.onLinkPropertiesChanged(network, linkProperties)
                    }
                }
            )
        }else{
            val networkInfo = connectivityManager.activeNetworkInfo
            isConnected = networkInfo!=null && networkInfo.isConnected
        }
    }

}
var isConnected:Boolean = true