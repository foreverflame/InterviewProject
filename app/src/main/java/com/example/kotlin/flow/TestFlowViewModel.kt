package com.example.kotlin.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @description:flow test
 * @author: huangyonghuang
 * @date: 2023/1/28
 */
class TestFlowViewModel : ViewModel() {
    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
    val state: StateFlow<Int> get() = _state

    private val _name: MutableStateFlow<String> = MutableStateFlow("第二个stateFlow")
    val name: MutableStateFlow<String> get() = _name

    fun download() {
        for (state in 0..5) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(200L * state)
                _state.value = state
            }
        }
    }
}