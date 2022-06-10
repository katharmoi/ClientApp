package io.appeiron.clientapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.appeiron.clientapp.data.ServerResponse
import io.appeiron.clientapp.data.Status
import io.appeiron.clientapp.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Observer to update UI
        val observer = Observer<ServerResponse<String>> { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    binding.txtResponse.text = response.data
                    binding.btnConnect.isEnabled = true
                }
                Status.ERROR -> {
                    binding.txtResponse.text = response.message
                    binding.btnConnect.isEnabled = true
                }
                Status.LOADING -> {
                    binding.btnConnect.isEnabled = false
                }
            }
        }

        //Observe LiveData
        viewModel.serviceLiveData.observe(this, observer)

        binding.btnConnect.setOnClickListener {
            viewModel.requestResponse()
        }
    }

}