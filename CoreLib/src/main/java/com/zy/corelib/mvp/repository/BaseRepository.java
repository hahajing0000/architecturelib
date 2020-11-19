package com.zy.corelib.mvp.repository;

import com.zy.corelib.mvp.model.IModel;

/**
 * @author:zhangyue
 * @date:2020/11/19
 */
public abstract class BaseRepository<M extends IModel> {
    protected M mModel;
    public BaseRepository(){
        mModel=CreateAndInitModel();
    }

    protected abstract M CreateAndInitModel();
}
