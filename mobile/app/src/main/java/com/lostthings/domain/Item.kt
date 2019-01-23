package com.lostthings.domain

data class Item(
    val addDate: String,
    val contact: String,
    val description: String,
    val foundDate: String?,
    val id: Int,
    val isReturned: Boolean,
    val location: String,
    val name: String,
    val thumbnail: String?,
    val userId: String
) {

    fun contains(text: String): Boolean {
        return name.toLowerCase().contains(text.toLowerCase())
    }
}
