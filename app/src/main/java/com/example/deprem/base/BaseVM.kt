package com.example.deprem.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import javax.inject.Inject


@HiltViewModel
open class BaseVM @Inject constructor() : ViewModel() {

    val job = Job()
    val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}