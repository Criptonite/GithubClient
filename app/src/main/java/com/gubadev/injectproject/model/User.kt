package com.gubadev.injectproject.model

import com.google.gson.annotations.SerializedName

class User(@SerializedName("login") val name: String,
           @SerializedName("avatar_url") val avatar: String)