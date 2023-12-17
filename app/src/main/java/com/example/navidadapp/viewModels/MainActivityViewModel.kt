package com.example.navidadapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _currentVideoPosition = MutableLiveData<Int>()

    fun setCurrentVideoPosition(position: Int) {
        _currentVideoPosition.value = position
    }

    fun getCurrentVideoPosition(): Int {
        return _currentVideoPosition.value ?: 0
    }
}
