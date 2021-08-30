package com.courses.applicationworkapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.courses.applicationworkapi.api.MyRetrofit
import com.courses.applicationworkapi.model.Product
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recycleProducts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleProducts = findViewById(R.id.recyler_products)
        recycleProducts.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData(){
        val call: retrofit2.Call<List<Product>> =
            MyRetrofit.instance?.ProductApi()?.getProductApi() as retrofit2.Call<List<Product>>

        call.enqueue(object : retrofit2.Callback<List<Product>>{


            override fun onFailure(call: retrofit2.Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }


            override fun onResponse(call: retrofit2.Call<List<Product>>, response: Response<List<Product>>) {
                val adapter = response.body()?.let { ProductAdapter(this@MainActivity, it.toList()) }
                recycleProducts.adapter = adapter

                }
            })



        }
    }
