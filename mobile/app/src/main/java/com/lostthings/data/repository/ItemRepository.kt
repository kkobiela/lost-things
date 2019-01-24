package com.lostthings.data.repository

import com.lostthings.data.service.ItemApi
import com.lostthings.data.service.mapper.ItemMapper
import com.lostthings.data.session.UserSession
import com.lostthings.domain.Item
import com.lostthings.domain.ItemToAdd
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ItemRepository(private val itemApi: ItemApi, private val userSession: UserSession) {

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

    fun returnItem(item: Item, onSuccess: (Item) -> Unit) {
        disposables.add(
            itemApi.changeItem(ItemMapper.map(item))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess(ItemMapper.map(it)) },
                    {
                        //TODO: Problem occurs
                        it.toString()
                    })
        )
    }

    var profileName: String
        get() = userSession.profileName
        set(value) {
            userSession.profileName = value
        }

    var profilePhotoUrl: String
        get() = userSession.profilePhotoUrl
        set(value) {
            userSession.profilePhotoUrl = value
        }

    var profileEmail: String
        get() = userSession.profileEmail
        set(value) {
            userSession.profileEmail = value
        }
}
