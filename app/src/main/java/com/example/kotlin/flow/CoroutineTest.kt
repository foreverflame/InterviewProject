package com.example.kotlin.flow

import android.util.Log
import kotlinx.coroutines.*

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/1/12
 */
class CoroutineTest {


    /**
     * CoroutineStart 协程的启动模式一共有4种 Default、lazy、atomic、undispatched
     */
    private fun testCoroutineStart() {
        val defaultJob = GlobalScope.launch {
            Log.d("defaultJob", "CoroutineStart.DEFAULT")
        }
        defaultJob.cancel()
        val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
            Log.d("lazyJob", "CoroutineStart.LAZY")
        }
        val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起前")
            delay(100)
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起后")
        }
        atomicJob.cancel()
        val undispatchedJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            Log.d("undispatchedJob", "CoroutineStart.UNDISPATCHED挂起前")
            delay(100)
            Log.d("undispatchedJob", "CoroutineStart.UNDISPATCHED挂起后")
        }
        undispatchedJob.cancel()
    }

    /**
     *  协程挂起
     */
//    private fun testUnDispatched(){
//        GlobalScope.launch(Dispatchers.Main){
//            val job = launch(Dispatchers.IO) {
//                Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
//                delay(100)
//                Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
//            }
//            Log.d("${Thread.currentThread().name}线程", "-> join前")
//            job.join()
//            Log.d("${Thread.currentThread().name}线程", "-> join后")
//        }
//    }

    /**
     * 给子协程设置启动模式
     */
    private fun testUnDispatched() {
        GlobalScope.launch(Dispatchers.Main) {
            val job = launch(Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
                Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
                delay(100)
                Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
            }
            Log.d("${Thread.currentThread().name}线程", "-> join前")
            job.join()
            Log.d("${Thread.currentThread().name}线程", "-> join后")
        }
    }


    /**
     * 协程作用域测试
     */
    private fun coroutineScopeTest() {
        val runBlockJob = runBlocking {
            Log.d("runBlocking", "启动一个协程")
        }
        Log.d("runBlockJob", "$runBlockJob")

        val launchJob = GlobalScope.launch {
            Log.d("launch", "启动一个协程")
        }
        Log.d("launchJob", "$launchJob")
        val asyncJob = GlobalScope.async {
            Log.d("async", "启动一个协程")
        }
        Log.d("asyncJob", "$asyncJob")
    }


    /**
     * 协同作用域
     */
    private fun testCoroutineScope2() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
        }
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            Log.d("scope", "--------- 1")
            launch(CoroutineName("scope2") + exceptionHandler) {
                Log.d("scope", "--------- 2")
                throw  NullPointerException("空指针")
                Log.d("scope", "--------- 3")
            }
            val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                Log.d("scope", "--------- 4")
                delay(2000)
                Log.d("scope", "--------- 5")
            }
            scope3.join()
            Log.d("scope", "--------- 6")
        }
    }

    /**
     * 主从
     */
    private fun testCoroutineScope3() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
        }
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            supervisorScope {
                Log.d("scope", "--------- 1")
                launch(CoroutineName("scope2")) {
                    Log.d("scope", "--------- 2")
                    throw  NullPointerException("空指针")
                    Log.d("scope", "--------- 3")
                    val scope3 = launch(CoroutineName("scope3")) {
                        Log.d("scope", "--------- 4")
                        delay(2000)
                        Log.d("scope", "--------- 5")
                    }
                    scope3.join()
                }
                val scope4 = launch(CoroutineName("scope4")) {
                    Log.d("scope", "--------- 6")
                    delay(2000)
                    Log.d("scope", "--------- 7")
                }
                scope4.join()
                Log.d("scope", "--------- 8")
            }
        }
    }


    /**
     * 主从作业
     */
    private fun testCoroutineScope4() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
        }
        val coroutineScope = CoroutineScope(SupervisorJob() + CoroutineName("coroutineScope"))
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            with(coroutineScope) {
                val scope2 = launch(CoroutineName("scope2") + exceptionHandler) {
                    Log.d("scope", "1--------- ${coroutineContext[CoroutineName]}")
                    throw  NullPointerException("空指针")
                }
                val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                    scope2.join()
                    Log.d("scope", "2--------- ${coroutineContext[CoroutineName]}")
                    delay(2000)
                    Log.d("scope", "3--------- ${coroutineContext[CoroutineName]}")
                }
                scope2.join()
                Log.d("scope", "4--------- ${coroutineContext[CoroutineName]}")
                coroutineScope.cancel()
                scope3.join()
                Log.d("scope", "5--------- ${coroutineContext[CoroutineName]}")
            }
            Log.d("scope", "6--------- ${coroutineContext[CoroutineName]}")
        }
    }

}