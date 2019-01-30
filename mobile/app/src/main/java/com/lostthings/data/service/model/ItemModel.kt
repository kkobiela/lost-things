package com.lostthings.data.service.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("add_date")
    val addDate: String,
    val contact: String,
    val description: String,
    @SerializedName("found_date")
    val foundDate: String?,
    val id: Int,
    @SerializedName("is_returned")
    val isReturned: Byte,
    val location: String,
    val name: String,
    val thumbnail: String?,
    @SerializedName("user_id")
    val userId: String
)
