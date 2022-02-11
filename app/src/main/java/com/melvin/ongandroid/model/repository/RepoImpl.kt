package com.melvin.ongandroid.model.repository

import android.content.Context
import com.melvin.ongandroid.businesslogic.data.DataSource
import com.melvin.ongandroid.businesslogic.vo.Resource
import com.melvin.ongandroid.model.New
import com.melvin.ongandroid.model.User
import com.melvin.ongandroid.model.response.Testimonials
import com.melvin.ongandroid.model.response.VerifyUser
import com.melvin.ongandroid.model.service.OnAPIResponse
import retrofit2.Response


class RepoImpl(private val dataSource:DataSource): Repo {

    override suspend fun postUser(user: User, context: Context?, onAPIResponse: OnAPIResponse) {
              dataSource.postRegister(user, context, onAPIResponse)
    }

    override suspend fun postToken(user: String, pass: String): Response<VerifyUser> {
        return dataSource.authUser(user,pass)
    }

    override suspend fun getNewsList(newName: String): Resource<List<New>> {
        return dataSource.getNewsByName(newName)
    }

    override suspend fun getFourTestimonials(): Response<Testimonials> {
        return dataSource.getFourTestimonials()
    }
}