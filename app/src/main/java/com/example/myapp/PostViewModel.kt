package com.example.myapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val repo = PostRepository()

    fun getPosts(): LiveData<List<PostModel>>{
        return repo.getPosts()
    }
}