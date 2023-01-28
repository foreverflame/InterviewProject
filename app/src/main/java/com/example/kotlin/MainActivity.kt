package com.example.kotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.widget.databinding.ActivityMain2Binding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val activityMain2Binding: ActivityMain2Binding by inflate()
    private val testFlowViewModel: TestFlowViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMain2Binding.apply {
            tvCoroutineStart.setOnClickListener {
                test1()
            }
        }
    }


    private fun test() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val flow = flow<String> { }.shareIn(
                    scope = lifecycleScope,
                    started = SharingStarted.Lazily,
                    replay = 1
                )
            }
        }
    }


    fun test1() {
        val job = lifecycleScope.launch {
            flow {
                for (i in 1 until 4) {
                    delay(100)
                    emit(i)
                }
            }.collect { value ->
                Log.d("test1", "value:${value}")
            }
        }

        job.cancel()
    }

    fun test2() {
        val job = lifecycleScope.launch {
            //内部调用的就是的flow{}
            flowOf(1, 2, 3).collect { value ->
                Log.d("test1", "value:${value}")
            }
        }
        job.cancel()
    }

    fun test3() {
        lifecycleScope.launch {
            flow {
                for (i in 1..3) {
                    Log.d(TAG, "flow :${currentCoroutineContext()}")
                    delay(100)
                    emit(i)
                }
            }.map {
                Log.d(TAG, "map:${currentCoroutineContext()}")
                it
            }.flowOn(Dispatchers.Default).collect { value ->
                Log.d(TAG, "collect:${currentCoroutineContext()} value :${value}")
            }
        }
    }

    /**
     * 总结：flow 操作符
     * 过度操作符：onStart onEach onComplete
     * 末端操作符：single toList toSet collect reduce fold fist 这些都是挂起函数
     */

    fun flowLabel() {
        lifecycleScope.launch {
            flow<Int> {

            }.onStart { }
                .onEach { }
                .onCompletion { }
        }
    }


    private fun test5() {
        lifecycleScope.launch {
            flow {
                Log.d("flow", "${currentCoroutineContext()}")
                emit(1)
            }.onStart {
                Log.d("onStart", "${currentCoroutineContext()}")
            }.onEach {
                Log.d("onEach", "$it")
                throw java.lang.NullPointerException("空指针")
            }
                .onCompletion { cause ->
                    Log.d("onCompletion", "$cause")
                }
                .collect {
                    Log.d("collect", "$it")
                }
        }
    }


    /**
     * 这种情况只打印 onstart-flow-oneach-oncomplete
     */
    private fun test6() {
        lifecycleScope.launch {
            flow {
                Log.d("flow", "${currentCoroutineContext()}")
                emit(1)
                throw java.lang.NullPointerException("空指针")
            }.onStart {
                Log.d("onStart", "${currentCoroutineContext()}")
            }.onEach {
                Log.d("onEach", "$it")
            }.catch { cause ->
                Log.d("catch", "$cause")
                emit(2)
            }
                .map {
                    Log.d("map", "$it")
                    throw  NullPointerException("第二个空指针")
                    it
                }
                .onCompletion { cause ->
                    Log.d("onCompletion", "$cause")
                }

                .collect {
                    Log.d("collect", "$it")
                }
        }
    }


    private fun test7() {
        lifecycleScope.launch {
            flow {
                Log.d("flow", "${currentCoroutineContext()}")
                emit(1)
                throw java.lang.NullPointerException("空指针")
            }.onStart {
                Log.d("onStart", "${currentCoroutineContext()}")
            }.onEach {
                Log.d("onEach", "$it")
            }.catch { cause ->
                Log.d("catch", "$cause")
                emit(2)
            }
                .map {
                    Log.d("map", "$it")
                    throw  NullPointerException("第二个空指针")
                    it
                }
                .catch { cause2 ->
                    Log.d("catch2", "$cause2")
                }.onCompletion { cause ->
                    Log.d("onCompletion", "$cause")
                }
                .collect {
                    Log.d("collect", "$it")
                }
        }
    }


    /**
     *
     */
    fun test8() {
        lifecycleScope.launch {
            (1 until 4).asFlow().transform {
                emit(it)
                emit("transform$it")
            }.collect {
                Log.d("transform", "$it")
            }
        }
    }

    fun test9() {
        val flow = flowOf("one", "two", "three", "four", null, "six")
        lifecycleScope.launch {
            flow.mapNotNull {
                it
            }.collect {
                Log.d("mapNotNull", it)
            }
        }
    }

    /**
     * 结合比较少的
     */
    fun test10() {
        val flow1 = (1..2).asFlow()
        val flow2 = flowOf("one", "two", "three")
        lifecycleScope.launch {
            flow2.zip(flow1) { value1, value2 ->
                "$value1:$value2"
            }.collect {
                Log.d("collect", it)
            }
        }
    }

    fun test11() {
        lifecycleScope.launch {
            (1..3).asFlow().take(2).collect { value ->
                Log.d("collect", "$value")
            }
        }
    }

    fun channel() {
        runBlocking {
            val channel = Channel<Int>()
            launch {
                for (x in 1..3) {
                    channel.send(x)
                }
            }
            launch {
                delay(100)
                channel.send(888)
                channel.send(999)
            }
            repeat(Int.MAX_VALUE) {
                println("receive :${channel.receive()}")
            }
            println("done")
        }
    }


}







