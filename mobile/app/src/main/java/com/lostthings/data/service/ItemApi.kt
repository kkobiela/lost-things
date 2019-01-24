package com.lostthings.data.service

import com.lostthings.data.service.model.ItemModel
import com.lostthings.data.service.model.ItemReturnModel
import com.lostthings.data.service.model.ItemToAddModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ItemApi {

    @GET("/things")
    fun getItems(): Single<List<ItemModel>>

    @POST("/things")
    fun addItem(@Body itemToAddModel: ItemToAddModel): Single<ItemModel>

    @PATCH("/things")
    fun changeItem(@Body itemModel: ItemModel): Single<ItemModel>
}
