package com.asefactory.wifidataapp

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        returnSsidFromConnectivityManager()
    }

    private fun returnSsidFromConnectivityManager() {
        val connectivityManager =
            applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                findViewById<CheckBox>(R.id.TRANSPORT_WIFI).isChecked = false
                findViewById<CheckBox>(R.id.TRANSPORT_CELLULAR).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_CAPTIVE_PORTAL).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_INTERNET).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_METERED).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_RESTRICTED).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_VPN).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_TRUSTED).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_VALIDATED).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_WIFI_P2P).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_ROAMING).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_SUSPENDED).isChecked = false
                findViewById<CheckBox>(R.id.NET_CAPABILITY_TEMPORARILY_NOT_METERED).isChecked = false
            }


            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                findViewById<CheckBox>(R.id.TRANSPORT_WIFI).isChecked =
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                findViewById<CheckBox>(R.id.TRANSPORT_CELLULAR).isChecked =
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_CAPTIVE_PORTAL).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_CAPTIVE_PORTAL)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_INTERNET).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_METERED).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_RESTRICTED).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_VPN).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_TRUSTED).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_VALIDATED).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                findViewById<CheckBox>(R.id.NET_CAPABILITY_WIFI_P2P).isChecked =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P)

                val NET_CAPABILITY_NOT_ROAMING =
                    findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_ROAMING)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    NET_CAPABILITY_NOT_ROAMING.isChecked =
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING)
                    NET_CAPABILITY_NOT_ROAMING.visibility = View.VISIBLE
                } else {
                    NET_CAPABILITY_NOT_ROAMING.visibility = View.GONE
                }

                val NET_CAPABILITY_NOT_SUSPENDED =
                    findViewById<CheckBox>(R.id.NET_CAPABILITY_NOT_SUSPENDED)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    NET_CAPABILITY_NOT_SUSPENDED.isChecked =
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED)
                    NET_CAPABILITY_NOT_SUSPENDED.visibility = View.VISIBLE
                } else {
                    NET_CAPABILITY_NOT_SUSPENDED.visibility = View.GONE
                }

                val NET_CAPABILITY_TEMPORARILY_NOT_METERED =
                    findViewById<CheckBox>(R.id.NET_CAPABILITY_TEMPORARILY_NOT_METERED)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    NET_CAPABILITY_TEMPORARILY_NOT_METERED.isChecked =
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TEMPORARILY_NOT_METERED)
                    NET_CAPABILITY_TEMPORARILY_NOT_METERED.visibility = View.VISIBLE
                } else {
                    NET_CAPABILITY_TEMPORARILY_NOT_METERED.visibility = View.GONE
                }
            }
        }

        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}