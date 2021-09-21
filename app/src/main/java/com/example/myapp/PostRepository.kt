package com.example.myapp

import androidx.lifecycle.MutableLiveData
import com.example.myapp.network.PostClient
import com.example.myapp.network.PostService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class PostRepository {
    private val service = PostClient.getClient().create(PostService::class.java)
    private val compositeDisposable = CompositeDisposable()
    private var data = MutableLiveData<List<PostModel>>()


    fun getPosts(): MutableLiveData<List<PostModel>> {
        service.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { posts ->
                if (posts != null) {
                    data.value = posts
                }

            }.addTo(compositeDisposable)
        return data
    }
}