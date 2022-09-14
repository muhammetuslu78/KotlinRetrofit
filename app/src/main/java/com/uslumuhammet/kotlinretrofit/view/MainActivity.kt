package com.uslumuhammet.kotlinretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uslumuhammet.kotlinretrofit.adapter.CryptoAdapter
import com.uslumuhammet.kotlinretrofit.databinding.ActivityMainBinding
import com.uslumuhammet.kotlinretrofit.model.Crypto
import com.uslumuhammet.kotlinretrofit.service.CryptoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CryptoAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoList : ArrayList<Crypto>?=null

    private var recyclerViewAdapter : CryptoAdapter?=null

    //Disposable
    private var compositeDisposable : CompositeDisposable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //cryptoList = ArrayList<Crypto>()
        compositeDisposable = CompositeDisposable();

        // 2fe2563ad335c0aff24ca8bb3dcff33f3a5a7857
        //curl "https://api.nomics.com/v1/markets/prices?key=2fe2563ad335c0aff24ca8bb3dcff33f3a5a7857

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
       binding.recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoApi::class.java)


        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))





        /**
         *
         * val call = service.getData()
         * call.enqueue(object : Callback<List<Crypto>> {
        override fun onResponse(call: Call<List<Crypto>>, response: Response<List<Crypto>>) {
        if (response.isSuccessful)
        {
        response.body()?.let {
        cryptoList = ArrayList(it)

        cryptoList?.let {
        recyclerViewAdapter = CryptoAdapter(cryptoList!!,this@MainActivity)

        for(cryptomodels : Crypto in cryptoList!!)
        {
        println(cryptomodels.currency)
        }
        }
        }
        }
        }

        override fun onFailure(call: Call<List<Crypto>>, t: Throwable) {
        t.printStackTrace()
        }

        })
         */

    }

    override fun OnItemClick(crypto: Crypto) {
        Toast.makeText(this,"Clicked ${crypto.currency}",Toast.LENGTH_LONG).show()
        println("Deneme")

        println("Experimental")
    }

    private fun handleResponse(cryptoList2: List<Crypto>)
    {
        cryptoList = ArrayList(cryptoList2)

        cryptoList?.let {
            recyclerViewAdapter = CryptoAdapter(cryptoList!!,this@MainActivity)
            binding.recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}