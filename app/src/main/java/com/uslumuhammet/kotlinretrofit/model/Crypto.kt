package com.uslumuhammet.kotlinretrofit.model

import com.google.gson.annotations.SerializedName

data class Crypto(
    //@SerializedName("currency")
    val currency : String,

    //@SerializedName("price")
    val price : String)