package com.example.rayna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rayna.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddProductViewModel @Inject constructor(private val addProductUseCase: AddProductUseCase): ViewModel() {

    //TODO: Add ViewModel logic

}