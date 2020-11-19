package com.zy.corelib.mvp.ui;

import android.os.Bundle;

import com.zy.corelib.mvp.presenter.BasePresenter;
import com.zy.corelib.mvp.presenter.Presenter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/11/19
 */
public abstract class BaseMVPActivity extends BaseActivity {
    private List<BasePresenter> mPresenters=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BuildPresenters(this);
    }


    /**
     * 绑定Presenter 支持多P层 达到P层复用的目的
     * @param object
     */
    private void BuildPresenters(Object object) {
        mPresenters=new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field:fields){
            Presenter annotation = field.getAnnotation(Presenter.class);
            if (annotation!=null){

                try {
                    Class<? extends BasePresenter> type= (Class<? extends BasePresenter>) field.getType();
                    Constructor<?>[] constructors = type.getConstructors();
                    BasePresenter presenter =null;
                    for (Constructor constructor:constructors){
                        presenter= (BasePresenter) constructor.newInstance(object);
                        break;
                    }
                    field.setAccessible(true);
                    field.set(this,presenter);
                    mPresenters.add(presenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
