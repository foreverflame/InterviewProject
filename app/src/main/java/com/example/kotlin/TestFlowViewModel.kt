package com.example.kotlin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/1/13
 */
class TestFlowViewModel : ViewModel() {

    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
    val state: StateFlow<Int> get() = _state
    val state2: StateFlow<Int> = _state  //会额外创建一个stateFlow的类型变量，来持有同一个对象的引用

    init {

    }


    override fun onCleared() {
        super.onCleared()
    }


}