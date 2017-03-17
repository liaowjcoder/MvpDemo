package com.zeal.mvp_login_advance.presenter;

import com.zeal.mvp_login_advance.view.MvpView;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 15:37
 * @描述 ${TODO} 
 */


/**
 * Presenter 接口定义了所有 Presnter 的共性功能，那就是与 View 建立连接和与 View 取消连接的关系。
 */
public interface Presenter<V extends MvpView> {

    /**
     * Presenter 与 View 建立联系
     */
    void attachView(V mvpView);

    /**
     * Presenter 与 View 断开连接
     */
    void detachView();

}
