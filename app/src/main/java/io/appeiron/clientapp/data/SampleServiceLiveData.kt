package io.appeiron.clientapp.data

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import androidx.lifecycle.LiveData
import io.appeiron.serverapp.IResponseCallback
import io.appeiron.serverapp.ISampleApi
import javax.inject.Inject

const val SERVICE_NAME = "sampleaidl"

/** 
 * Custom LiveData class responsible from Service Connection
 */
class SampleServiceLiveData @Inject constructor(private val app: Application) : LiveData<String>(),
    ServiceConnection {

    private var cb: IResponseCallback.Stub = object : IResponseCallback.Stub() {
        override fun onSuccess(msg: String?) {
            postValue(msg)
        }

        override fun onFailure(msg: String?) {
            postValue(msg)
        }

    }

    private var serviceBinding: ISampleApi? = null


    override fun onActive() {
        super.onActive()
        val intent = Intent(SERVICE_NAME)
        val pack = ISampleApi::class.java.`package`
        pack?.let {
            intent.setPackage(pack.name)
            app.applicationContext.bindService(
                intent, this, Context.BIND_AUTO_CREATE
            )
        }
    }

    override fun onInactive() {
        serviceBinding = null
        app.unbindService(this)
        super.onInactive()
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        serviceBinding = ISampleApi.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        serviceBinding = null
    }

    fun requestResponse() {
        try {
            serviceBinding?.respond(cb)
        } catch (e: RemoteException) {
            Log.e(javaClass.simpleName, "Failure on request", e)
        }
    }
}