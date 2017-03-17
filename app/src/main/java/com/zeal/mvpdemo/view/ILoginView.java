package com.zeal.mvpdemo.view;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 1:25
 * @描述 ${TODO} 
 */

public interface ILoginView {
    String getUsername();

    String getPassword();

    void showLoading(String msg);

    void hideLoading();

    void showResult(String result);
}
