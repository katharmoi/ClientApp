package io.appeiron.clientapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.appeiron.clientapp.InstantExecutorExtension
import io.appeiron.clientapp.data.SampleServiceLiveData
import io.appeiron.clientapp.data.ServerResponse
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(value = [InstantExecutorExtension::class])
internal class MainViewModelTest {

    lateinit var underTest: MainViewModel

    //Mock LiveData
    val serviceLiveData: SampleServiceLiveData = mockk()

    //Mock observer for LiveData
    val observer: Observer<ServerResponse<String>> = mockk()


    @Before
    fun setUp() {
        underTest = MainViewModel(serviceLiveData)
    }

    /**
     * Testing onChanged() for [LiveData]
     *
     */
    @Test
    fun `Verify livedata values changes`() {
        underTest.serviceLiveData.observeForever(observer)
        underTest.requestResponse()
        //verify(observer).onChanged(ServerResponse.success(Any))
    }

    /**
     * Test assert values [LiveData] items on [MainViewModel] when request response
     *
     */

}