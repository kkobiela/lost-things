package com.lostthings.domain

data class ItemToAdd(
    val contact: String,
    val description: String,
    val location: String,
    val name: String,
    val thumbnail: String?,
    val userId: String
)
