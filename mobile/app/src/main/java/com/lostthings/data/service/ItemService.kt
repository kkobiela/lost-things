package com.lostthings.data.service

class ItemService {

    val itemApi by lazy {
        ServiceGenerator(BASE_URL).createService(ItemApi::class.java)
    }

    companion object {
        private const val BASE_URL = "http://lostthings.herokuapp.com/"
    }
}
