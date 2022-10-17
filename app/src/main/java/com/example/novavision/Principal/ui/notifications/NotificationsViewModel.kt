package com.example.novavision.Principal.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel()
{
    private val _text = MutableLiveData<String>().apply {
        value = "Este fragmento tendrá las notificaciones"
    }
    val text: LiveData<String> = _text

}