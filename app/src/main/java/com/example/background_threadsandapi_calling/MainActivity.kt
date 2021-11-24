package com.example.background_threadsandapi_calling

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    var i: Int = 0
    val url = "https://api.agify.io?name=bella"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClick.setOnClickListener {
            tvCounter.text = "${tvCounter.text.toString().toInt() + 1}"
        }
        getData()

    }

    fun getData() {

        val okhttpClient = OkHttpClient()

        val request = Request.Builder().url(url).build()
        okhttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                print("eeeeeeeeeee*$e")
                Log.i("hello","********$e")


            }

            override fun onResponse(call: Call, response: Response){
                print("*******111111111*${response.body}")
                Log.i("hello","********${response.body}")

                if (response.isSuccessful) {
                    val myresponse = response.body?.string()

                    runOnUiThread(Runnable {
                        textView.text = myresponse
                    })
                }
            }
        })
    }


}
