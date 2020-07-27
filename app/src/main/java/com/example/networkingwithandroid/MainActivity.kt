package com.example.networkingwithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.github.com/users/kunal-kushwaha")
            .build()

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){okHttpClient.newCall(request).execute().body?.string()}
            //Log.i("Networking", "${response.body?.toString()}")

            val obj = JSONObject(response)
            //obj.getString("login")
//          Log.i("Networking", obj.getString("login"));

            val image = obj.getString("avatar_url")
            val login = obj.getString("login")
            val name = obj.getString("name")
            val showImage = Picasso.get()
                .load(image)
                .into(imageView)

            textView.text = name
            textView2.text = login

        }

    }
}