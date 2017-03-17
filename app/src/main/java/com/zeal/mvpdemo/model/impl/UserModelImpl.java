package com.zeal.mvpdemo.model.impl;

import android.os.Handler;

import com.zeal.mvpdemo.Callback;
import com.zeal.mvpdemo.model.IUserModel;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 1:28
 * @描述 ${TODO} 
 */

public class UserModelImpl implements IUserModel {
    Handler mHandler = new Handler();
    @Override
    public void login(final String username, final String password, final Callback callback) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if("zeal".equals(username)&&"123456".equals(password)) {
                    callback.onSuccess();
                }else{
                    callback.onFail("用户名或者密码错误");
                }
            }
        },2000);
    }
}
