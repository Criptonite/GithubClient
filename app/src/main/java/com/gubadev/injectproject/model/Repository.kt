package com.gubadev.injectproject.model

import com.google.gson.annotations.SerializedName

class Repository(@SerializedName("name") val name: String,
                 @SerializedName("owner") val user: User,
                 @SerializedName("pushed_at") val lastModified: String)