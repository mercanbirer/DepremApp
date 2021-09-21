package com.example.depremapp.view.deprem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depremapp.data.Resource
import com.example.depremapp.data.Status
import com.example.depremapp.model.Deprem
import com.example.depremapp.repository.DepremRepository
import com.github.ajalt.timberkt.e
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
@ExperimentalCoroutinesApi
class DepremVM @Inject constructor(
    private val depremRepository: DepremRepository,
) : ViewModel() {

    val apiDeprem: MutableLiveData<List<Deprem>> = MutableLiveData()

    fun getDepremView(
        api: String
    ): LiveData<List<Deprem>> {
        return if (apiDeprem.value?.size == null) {
            viewModelScope.launch {
                depremRepository.getDeprems(api).collect {
                    if (it.status == Status.SUCCESS) {
                        e { "denememe" }
                        apiDeprem.postValue(it.data)
                    } else if (it.status == Status.ERROR) {
                        e { "patladı ${it.throwable}" }
                    }

                }
            }
            apiDeprem
        } else {
            apiDeprem
        }
    }


}
