package com.example.android;


public class Android_inter {


    /**
     * mvc，mvp，mvvm 框架之间进行对比
     * mvvm 查看官方文档
     * https://www.jianshu.com/p/c0988e7f31fd
     */
    //=================================================

    /**
     * tcp 和udp ，http和https 区别，网络编程模块
     * 链接：https://blog.csdn.net/sifanchao/article/details/82285018?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     */


    //=================================================

    /**
     * 屏幕适配问题，今日头条的屏幕适配方案，真正理解该方案的原理
     * https://www.jianshu.com/p/55e0fca23b4f
     * https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
     * 如果每个 View 的 dp 值是固定不变的，那我们只要保证每个设备的屏幕总 dp 宽度不变，就能保证每个 View 在所有分辨率的屏幕上与屏幕的比例都保持不变，从而完成等*    * 比例适配，并且这个屏幕总 dp 宽度如果还能保证和设计图的宽度一致的话，那我们在布局时就可以直接按照设计图上的尺寸填写 dp 值
     */

    //=================================================


    /**
     * android 多种设计模式
     * https://www.jianshu.com/p/b8c578b07fbc
     */


    /**
     * double check
     * https://www.jianshu.com/p/7b901261fc2d
     */
    public static class SingleInstance {

        //volatile 禁止指令重排序
        private volatile static SingleInstance instance = null;

        private SingleInstance() {
        }

        public static SingleInstance getInstance() {
            //当 instance 为 null 时，两个线程可以并发地进入 if 语句内部
            if (instance == null) {
                synchronized (SingleInstance.class) {
                    if (instance == null) {
                        instance = new SingleInstance();
                    }
                }
            }
            return instance;
        }
    }


    /**
     * 建造者模式
     * 将一个复杂对象的构建和他的表示分开
     */
    public static class Company {
        int age;

        Company(Builder builder) {
            this.age = builder.age;
        }

        public static class Builder {

            int age;

            public Builder setAge(int age) {
                this.age = age;
                return this;
            }

            private Company build() {
                return new Company(this);
            }
        }
    }


    /**
     * 观察者模式
     * https://www.jianshu.com/p/8f32da74cd8b
     */


    /**
     * 源码解析之leakcanary
     * https://www.jianshu.com/p/70de36ea8b31
     */

    /**
     * retrofit
     * https://www.jianshu.com/p/0c055ad46b6c
     * https://www.jianshu.com/p/45cb536be2f4
     *
     */

    /**
     * okhttp okhtt 二次封装 okhttProjectDemo
     * https://www.jianshu.com/p/37e26f4ea57b
     * https://www.jianshu.com/p/867b11a962ee
     */


    /**
     * 线程池
     * https://www.cnblogs.com/cdf-opensource-007/p/8769777.html
     */

    /**
     * 接口和抽象类的区别
     * https://blog.csdn.net/fenglibing/article/details/2745123?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     *
     */

    /**
     * service 启动方式
     * https://www.jianshu.com/p/4c798c91a613
     * https://www.jianshu.com/p/95ec2a23f300
     */


    /**
     * binder  原理解析
     * https://www.jianshu.com/p/af2993526daf
     */

    /**
     * jvm 垃圾回收机制
     * https://blog.csdn.net/yubujian_l/article/details/80804708
     */


    /**
     * linkhashmap lrucache linkhashmap 源码
     * https://www.jianshu.com/p/b49a111147ee
     * https://www.jianshu.com/p/8f4f58b4b8ab
     */


    /**
     * webview jsbridge
     * https://zhuanlan.zhihu.com/p/38046261
     */

    /**
     * 注解
     * https://blog.csdn.net/briblue/article/details/73824058
     */


    /**
     * 异常处理机制
     *https://blog.csdn.net/hguisu/article/details/6155636?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     */

    /**
     * 序列化
     * https://www.jianshu.com/p/287acb9e396f
     */


    /**
     * 广播，service，contentprovider
     * https://www.jianshu.com/p/53e6abd8bfc7
     */


    /**
     * 类加载器 tink原理，双亲委托
     * https://www.jianshu.com/p/6121858214f2?tdsourcetag=s_pctim_aiomsg
     * https://www.jianshu.com/p/704cac3eb13d
     * https://www.jianshu.com/p/92227738f270
     * http://liuwangshu.cn/application/classloader/1-java-classloader-.html
     */


    /**
     * android 事件传递机制，view的绘制流程，事件冲突
     *https://blog.csdn.net/cufelsd/article/details/89471402
     * https://blog.csdn.net/u010386612/article/details/50548977?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     *
     */

    /**
     * 数据库升级
     * https://www.jianshu.com/p/3fef519f3d8e
     */


    /**
     * 泛型
     * https://www.jianshu.com/p/5972220efc9a
     */

    /**
     * 反射
     * https://www.jianshu.com/p/9be58ee20dee
     */


    /**
     * handlerthread ,asyntask,intentsevice,
     *https://www.jianshu.com/p/13ce5e205122 asyntask 是串行的
     */


    /**
     * okhttp 封装
     * https://www.jianshu.com/p/ddbf69d1c9d1
     */



    /**
     * android 属性动画，差值器，估值器
     * https://www.jianshu.com/p/2412d00a0ce4
     * https://www.jianshu.com/p/7c95342f4bc2
     * https://www.jianshu.com/p/ab5785f017b2
     */


    /**
     * 自定义控件
     *
     */


    /**
     * jni ndk 开发
     *
     */

    /**
     * 性能优化 leakcanary,profiler
     * https://blog.csdn.net/Leo_Liang_jie/article/details/93871361
     * https://www.jianshu.com/p/3e8f7dbb0dc7 webview内存泄漏原因
     *
     */


    /**
     * 图片相关 glide 源码,retrofit源码，okhttp源码，leakcanary，blockcanary,rxjava
     * https://blog.csdn.net/guolin_blog/article/details/53939176/  glide 源码解析
     * https://me.csdn.net/sinyu890807
     * https://blog.csdn.net/qq_15827013/article/details/97893860  glide 大致流程
     */



    /**
     * android recyclerview  源码解析
     * https://www.jianshu.com/p/c52b947fe064
     * https://blog.csdn.net/lmj623565791/article/details/45059587
     * https://www.jianshu.com/p/efe81969f69d
     */


    /**
     * activity 生命周期和启动模式
     * https://blog.csdn.net/qq_29092191/article/details/80901610
     */

    /**
     * 垃圾回收算法
     * https://www.cnblogs.com/swordfall/p/10731728.html
     */

    /**
     * java 产生死锁的原因
     * https://blog.csdn.net/wljliujuan/article/details/79614019#%E4%B8%80%E4%BB%80%E4%B9%88%E6%98%AF%E6%AD%BB%E9%94%81
     */



    /**
     * 组件化 Arouter 学习阿里的路由
     * https://www.jianshu.com/p/6021f3f61fa6
     */

    /**
     * 直播协议 rtmp，hls，flv，播放器sdk的封装，烈焰弹幕
     * https://www.jianshu.com/p/53581512ba3f
     * https://www.jianshu.com/p/daf0a61cc1e0
     * https://www.cnblogs.com/lidabo/p/9018490.html
     * https://blog.csdn.net/abc0432136/article/details/101264211 首帧加载速率问题
     */




}
