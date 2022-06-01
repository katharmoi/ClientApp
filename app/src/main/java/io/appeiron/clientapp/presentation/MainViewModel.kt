package io.appeiron.clientapp.presentation

import android.os.RemoteException
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.appeiron.clientapp.data.SampleServiceLiveData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val serviceLiveData: SampleServiceLiveData
) : ViewModel() {

    fun requestResponse() {
        try {
            serviceLiveData.requestResponse()
        } catch (e: RemoteException) {
            Log.e(javaClass.simpleName, "Failure on request", e)
        }

    }
}