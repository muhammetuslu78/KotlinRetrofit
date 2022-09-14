package com.uslumuhammet.kotlinretrofit.service

import com.uslumuhammet.kotlinretrofit.model.Crypto
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoApi {

    // 2fe2563ad335c0aff24ca8bb3dcff33f3a5a7857
    //curl "https://api.nomics.com/v1/markets/prices?key=2fe2563ad335c0aff24ca8bb3dcff33f3a5a7857

        /*@GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
        fun getData() : Call<List<Crypto>>*/

        @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
        fun getData() : Observable<List<Crypto>>


}