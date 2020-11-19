package com.zy.corelib.mvp.presenter;

import com.zy.corelib.mvp.repository.BaseRepository;
import com.zy.corelib.mvp.view.IView;

/**
 * @author:zhangyue
 * @date:2020/11/19
 */
public abstract class BasePresenter<R extends BaseRepository,V extends IView> {
    protected R mRepository;
    protected V mView;
    public BasePresenter(V _view){
        mView=_view;
        mRepository=CreateAndInitRepository();
    }

    protected abstract R CreateAndInitRepository();

}
