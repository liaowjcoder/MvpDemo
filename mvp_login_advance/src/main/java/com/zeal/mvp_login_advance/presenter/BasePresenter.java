package com.zeal.mvp_login_advance.presenter;

import com.zeal.mvp_login_advance.view.MvpView;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 15:39
 * @描述 ${TODO} 
 */


/**
 * 建立 attachView 和 detachView 用于将 Presenter 和 View 建立关联。
 * 在调用业务逻辑时调用 checkViewAttached 方法判断 View 是否建立关联。
 * 在 Model 层将数据返回到 Presenter 层之后，Presenter 操作 View，需要
 * 先通过 isViewAttached 判断 View 是否和 Presenter 还保持连接状态。
 * 这种方式可以解决的问题就是空指针问题的出现。
 */
public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {

    protected V mMvpView;

    @Override
    public void attachView(V mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    /**
     * 判断 View 是否与 Presenter 建立连接
     */
    protected boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    /**
     * checkViewAttached 在调用业务逻辑前先判断 View 是否已经和 Presenter 已经
     * 建立连接。
     */
    protected void checkViewAttached() {
        if (! isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }
}
