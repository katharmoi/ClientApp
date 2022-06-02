package io.appeiron.clientapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.appeiron.clientapp.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Observer to update server response text
        val observer = Observer<String> { response ->
            //Update UI
            binding.txtResponse.text = response
        }

        //Observe LiveData
        viewModel.serviceLiveData.observe(this, observer)

        binding.btnConnect.setOnClickListener {
            viewModel.requestResponse()
        }
    }

}