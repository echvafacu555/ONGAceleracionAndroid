package com.melvin.ongandroid.viewmodel
import androidx.lifecycle.*
import com.melvin.ongandroid.businesslogic.vo.Resource
import com.melvin.ongandroid.model.repository.Repo
import kotlinx.coroutines.Dispatchers
import com.melvin.ongandroid.model.Slides
import com.melvin.ongandroid.model.Testimonials
import com.melvin.ongandroid.model.response.UserName
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: Repo) : ViewModel() {
    var testimonials = MutableLiveData<Testimonials>()
    var testimonialsException = MutableLiveData<Throwable>()


    var slides = MutableLiveData<Slides>()
    var slidesExcep=MutableLiveData<Throwable>()

    var userName= MutableLiveData<Resource<UserName>>()

    fun getSlides(){
        viewModelScope.launch {
            try {
                val call = repo.getSlides()
                if (call.isSuccessful) {
                    slides.value= call.body()
                }
            } catch (e: Exception) {
                slidesExcep.value = e
            }
        }
    }


    val fetchNewsList= liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getNewsList("hola"))
        }catch (e:Exception) {
            emit(Resource.Failure(e))
        }
    }

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

    fun fetchUser(email:String?){
        viewModelScope.launch {
            try {
                userName.value=repo.getUser(email)
            } catch (e: Exception) {
                userName.value= Resource.Failure(e)
            }
        }
    }

}