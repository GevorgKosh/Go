package com.example.myapp.network

import com.example.myapp.PostModel
import io.reactivex.Observable
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Observable<List<PostModel>>

}