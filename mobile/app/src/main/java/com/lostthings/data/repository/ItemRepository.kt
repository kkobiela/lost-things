package com.lostthings.data.repository

import com.lostthings.data.service.ItemApi
import com.lostthings.data.service.mapper.ItemMapper
import com.lostthings.domain.Item
import com.lostthings.domain.ItemToAdd
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ItemRepository(private val itemApi: ItemApi) {

    val disposables by lazy { CompositeDisposable() }

    fun getItems(onSuccess: (List<Item>) -> Unit, onError: (Throwable) -> Unit) {
        disposables.add(
            itemApi.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess(ItemMapper.map(it)) },
                    { onError(it) })
        )
    }

    fun addItem(itemToAdd: ItemToAdd, onSuccess: (Item) -> Unit, onError: (Throwable) -> Unit) {
        disposables.add(
            itemApi.addItem(ItemMapper.map(itemToAdd))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess(ItemMapper.map(it)) },
                    { onError(it) })
        )
    }
}
