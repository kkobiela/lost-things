package com.lostthings.data.service.mapper

import com.lostthings.data.service.model.ItemModel
import com.lostthings.data.service.model.ItemToAddModel
import com.lostthings.domain.Item
import com.lostthings.domain.ItemToAdd

object ItemMapper {

    fun map(models: List<ItemModel>): List<Item> {
        val cards = mutableListOf<Item>()
        models.forEach {
            cards.add(map(it))
        }
        return cards
    }

    fun map(model: ItemModel) = Item(
        model.addDate,
        model.contact,
        model.description,
        model.foundDate,
        model.id,
        when (model.isReturned) {
            0.toByte() -> false
            else -> true
        },
        model.location,
        model.name,
        model.thumbnail,
        model.userId
    )

    fun map(model: ItemToAdd) = ItemToAddModel(
        model.contact,
        model.description,
        model.location,
        model.name,
        model.thumbnail,
        model.userId
    )

    fun map(model: Item) = ItemModel(
        model.addDate,
        model.contact,
        model.description,
        model.foundDate ?: model.addDate,
        model.id,
        1,
        model.location,
        model.name,
        model.thumbnail,
        model.userId
    )
}
