package io.appeiron.clientapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.appeiron.clientapp.data.SampleServiceLiveData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val serviceLiveData: SampleServiceLiveData
) : ViewModel() {


    fun requestResponse() {
        serviceLiveData.getResponse()
    }
}