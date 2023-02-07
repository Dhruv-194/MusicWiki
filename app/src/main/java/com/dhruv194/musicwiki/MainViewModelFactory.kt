package com.dhruv194.musicwiki

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dhruv194.musicwiki.repository.Repository
import com.dhruv194.musicwiki.viewmodel.MainViewModel

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}