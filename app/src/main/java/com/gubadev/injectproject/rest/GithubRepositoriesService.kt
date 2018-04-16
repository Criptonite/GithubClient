package com.gubadev.injectproject.rest

import com.gubadev.injectproject.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRepositoriesService {

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String): Observable<List<Repository>>
}