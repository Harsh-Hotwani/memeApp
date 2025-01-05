package com.example.retrofitpro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofitpro.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        getMemeData()
        binding.newMemeButton.setOnClickListener{
            getMemeData()
        }
    }

    private fun getMemeData() {
        RetrofitObject.apiInterface.getData().enqueue(object : Callback<RetrofirDataClass?> {
            override fun onResponse(
                call: Call<RetrofirDataClass?>,
                response: Response<RetrofirDataClass?>
            ) {
                binding.memeTitle.text = response.body()?.title
                binding.memeAuthor.text = response.body()?.author
                Glide.with(this@MainActivity).load(response.body()?.url).into(binding.memeImage)
            }

            override fun onFailure(call: Call<RetrofirDataClass?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}