package com.zy.corelib.mvp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author:zhangyue
 * @date:2020/11/19
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen()){
            hideActionStatusBar();
            hideBottomStatusBar();
        }
        setContentView(getLayoutId());
        initView(savedInstanceState);
        initData();
        initEvent();
    }

    /**
     * 设置布局资源id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 是否全屏
     * @return
     */
    protected abstract boolean isFullScreen();

    /**
     * 初始化页面事件
     */
    protected abstract void initEvent();

    /**
     * 初始化页面数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 路由到指定activity
     * @param target
     */
    public void routeActivity(Class<?> target){
        startActivity(new Intent(this,target));
    }

    /**
     * 路由到指定Activity并携带参数
     * @param target
     * @param _bundle
     */
    public void routeActivity(Class<?> target,Bundle _bundle){
        Intent intent=new Intent(this,target);
        intent.putExtra("data",_bundle);
        startActivity(intent);
    }

    /**
     * 显示路由到指定Activity
     * @param packageName
     * @param action
     */
    public void routeActivity(String packageName,String action){
        Intent intent=new Intent();
        intent.setPackage(packageName);
        intent.setAction(action);
        startActivity(intent);
    }

    /**
     * 显示路由到指定Activity并携带参数
     * @param packageName
     * @param action
     * @param _bundle
     */
    public void routeActivity(String packageName,String action,Bundle _bundle){
        Intent intent=new Intent();
        intent.setPackage(packageName);
        intent.setAction(action);
        intent.putExtra("data",_bundle);
        startActivity(intent);
    }

    /**
     * 获取控件
     * @param viewId
     * @param <V>
     * @return
     */
    public <V> V $(int viewId){
        return (V)findViewById(viewId);
    }

    /**
     * 显示消息
     * @param msg
     */
    public void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长消息
     * @param msg
     */
    public void showLongMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    /**
     * 隐藏ActionBar和StatusBar
     */
    private void hideActionStatusBar() {
        //set no title bar 需要在setContentView之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //如果上面的不起作用，可以换成下面的。
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        if (getActionBar()!=null) {
            getActionBar().hide();
        }
        //no status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 隐藏 NavigationBar和StatusBar
     */
    protected void hideBottomStatusBar() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
