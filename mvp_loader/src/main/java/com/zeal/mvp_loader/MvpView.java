package com.zeal.mvp_loader;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 15:33
 * @描述 ${TODO} 
 */


/**
 * 定义多个页面共性 UI
 */
public interface MvpView {

    /**
     * 显示 loadding 提示框
     */
    void showLoading(String msg);

    /**
     * 隐藏 loading 提示框
     */
    void hideLoading();

    /**
     * 显示错误信息
     */
    void showError(String errMsg);

}
