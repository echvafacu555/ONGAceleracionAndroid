package com.melvin.ongandroid.viewmodel

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.ongandroid.businesslogic.data.DataSource
import com.melvin.ongandroid.model.repository.RepoImpl
import com.melvin.ongandroid.model.Testimonials
import kotlinx.coroutines.launch

class TestimonialsViewModel: ViewModel() {
    var repo = RepoImpl(DataSource())
    var testimonials = MutableLiveData<Testimonials>()
    var testimonialsException = MutableLiveData<Throwable>()

    fun getTestimonials(){
        viewModelScope.launch {
            try {
                val call = repo.getFourTestimonials()
                if (call.isSuccessful) {
                    testimonials.value= call.body()
                }
            } catch (e: Exception) {
                testimonialsException.value = e
            }
        }
    }


    val liveDataDialog = MutableLiveData<Testimonials>()




}