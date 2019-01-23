package com.lostthings.data.service.model

import com.google.gson.annotations.SerializedName

data class ItemToAddModel(
    val contact: String,
    val description: String,
    val location: String,
    val name: String,
    val thumbnail: String?,
    @SerializedName("user_id")
    val userId: String
)
