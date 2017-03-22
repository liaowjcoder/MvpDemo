package com.zeal.mvp_loader.model;


import com.zeal.mvp_loader.Callback;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 1:26
 * @描述 ${TODO} 
 */

public interface IUserModel {

    void login(String username, String password, Callback callback);

}
