package io.appeiron.clientapp.presentation

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import io.appeiron.clientapp.ISampleApi
import io.appeiron.clientapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ServiceConnection {

    private lateinit var binding: ActivityMainBinding
    private var iRemoteService: ISampleApi? = null
    private var connected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

        iRemoteService = ISampleApi.Stub.asInterface(service)
        connected = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        iRemoteService = null
        connected = false
    }
}