package com.lostthings.data.service

import com.lostthings.data.service.model.ItemModel
import com.lostthings.data.service.model.ItemReturnModel
import com.lostthings.data.service.model.ItemToAddModel
import io.reactivex.Single
import retrofit2.http.*

interface ItemApi {

    @GET("/things")
    fun getItems(): Single<List<ItemModel>>

    @Headers("Content-Type: application/json")
    @POST("/things")
    fun addItem(@Body itemToAddModel: ItemToAddModel): Single<List<ItemModel>>

    @Headers("Content-Type: application/json")
    @PATCH("/things")
    fun changeItem(@Body itemModel: ItemModel): Single<List<ItemModel>>
}
