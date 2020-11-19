package com.zy.corelib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.util.Stack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * @author:zhangyue
 * @date:2020/11/19
 */
public abstract class BaseApplication extends Application {
    protected Stack<Activity> activities=null;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        activities=new Stack<>();
        registerActivityLifecycleCallbacks(CreateAndInitLifecycleCallBacks());
        Thread.setDefaultUncaughtExceptionHandler(CreateAndInitUncaughtException());
    }

    /**
     * 捕获未捕获异常
     * @return
     */
    protected abstract Thread.UncaughtExceptionHandler CreateAndInitUncaughtException();

    /**
     * 监听Activity生命周期
     * @return
     */
    protected ActivityLifecycleCallbacks CreateAndInitLifecycleCallBacks(){
        ActivityLifecycleCallbacks callbacks=new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                //入栈
                activities.add(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                //出栈
                activities.remove(activity);
            }
        };
        return callbacks;
    }

    /**
     * 获取APP 上下文
     * @return
     */
    public static Context getAppContext(){
        return mContext;
    }

    /**
     * 主线程Handler
     */
    Handler H=new Handler(Looper.getMainLooper());

    /**
     * 运行在UI线程方法
     * @param runnable
     */
    public void runOnMainThread(Runnable runnable){
        H.post(runnable);
    }

    /**
     * 获取栈顶Activity
     * @return
     */
    public Activity getTopActivity(){
        return activities.lastElement();
    }

    /**
     * 退出APP
     */
    public void ExitApp(){
        for (Activity activity:
             activities) {
            activity.finish();
        }
    }

}
