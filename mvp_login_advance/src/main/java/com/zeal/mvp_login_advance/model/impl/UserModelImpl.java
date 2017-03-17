package com.zeal.mvp_login_advance.model.impl;

import android.os.Handler;

import com.zeal.mvp_login_advance.Callback;
import com.zeal.mvp_login_advance.model.IUserModel;


/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 1:28
 * @描述 ${TODO} 
 */

public class UserModelImpl implements IUserModel {
    Handler mHandler = new Handler();

    @Override
    public void login(final String username, final String password, final Callback callback) {
        //检测网路是否可用
        //检测本地数据库是否可用等。
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("zeal".equals(username) && "123456".equals(password)) {
                    callback.onSuccess();
                } else {
                    callback.onFail("用户名或者密码错误");
                }
            }
        }, 2000);
    }
}
