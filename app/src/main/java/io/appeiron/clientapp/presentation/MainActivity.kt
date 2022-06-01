package io.appeiron.clientapp.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.appeiron.clientapp.databinding.ActivityMainBinding
import io.appeiron.serverapp.IResponseCallback
import io.appeiron.serverapp.ISampleApi

const val SERVICE_NAME = "sampleaidl"

class MainActivity : AppCompatActivity(), ServiceConnection {

    private lateinit var binding: ActivityMainBinding
    private var serviceBinding: ISampleApi? = null
    private var connected = false

    private var cb: IResponseCallback.Stub = object : IResponseCallback.Stub() {

        override fun onSuccess(msg: String?) {
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "Got the response",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        override fun onFailure(msg: String?) {
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "Failed!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindToRemoteService()

        binding.btnConnect.setOnClickListener {
            try {
                serviceBinding?.respond(cb)
            } catch (e: RemoteException) {
                Log.e(localClassName, "Failure on request", e)
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        serviceBinding = ISampleApi.Stub.asInterface(service)
        binding.btnConnect.isEnabled = true
        connected = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        disconnect()
    }

    override fun onDestroy() {
        unbindService(this)
        disconnect()
        super.onDestroy()
    }

    private fun bindToRemoteService() {
        val intent = Intent(SERVICE_NAME)
        val pack = ISampleApi::class.java.`package`
        pack?.let {
            intent.setPackage(pack.name)
            applicationContext?.bindService(
                intent, this, Context.BIND_AUTO_CREATE
            )
        }
    }


    private fun disconnect() {
        serviceBinding = null
        binding.btnConnect.isEnabled = false
        connected = false
    }
}