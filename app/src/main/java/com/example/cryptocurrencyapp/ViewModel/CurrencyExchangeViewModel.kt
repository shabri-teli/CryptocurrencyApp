package com.example.cryptocurrencyapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.Network.CurrencyApi
import kotlinx.coroutines.launch

class CurrencyExchangeViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        getCurrencyData()
    }

    private fun getCurrencyData() {
        val headers = mapOf(
            "Accept" to "application/json",
            "X-CMC_PRO_API_KEY" to "8ceedb2b-e7bd-4843-b902-8a18feedf454"
        )
        val params = mapOf(
            "start" to "1",
            "limit" to "5000",
            "convert" to "USD"
        )
        viewModelScope.launch {
            try {
                val listResult = CurrencyApi.retrofitService.getCurrencyList(headers, params)
                _status.value = "Success: ${listResult} "
                Log.d("statusValue in try", "${_status.value}")
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.d("statusValue", "in catch ${_status.value}")
            }

            /*CurrencyApi.retrofitService.getCurrencyList(limit = 10, page = 0).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val responseBodyString = response.body()?.string()
                        Log.d("statusValue","$responseBodyString")
                        _status.value = responseBodyString.toString()
                        // Handle the response string
                    } else {
                        // Handle the error
                        _status.value = "error"
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // Handle the failure
                    _status.value = "$call,$t"
                }
            })*/
        }
    }
}