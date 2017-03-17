package com.zeal.mvpdemo.presenter.impl;

import com.zeal.mvpdemo.Callback;
import com.zeal.mvpdemo.view.ILoginView;
import com.zeal.mvpdemo.model.IUserModel;
import com.zeal.mvpdemo.presenter.ILoginPresenter;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 1:32
 * @描述 ${TODO} 
 */


/*
 * LoginPresenter:是 ILoginPresenter 的实现类，它负责处理业务逻辑，起连接 View 和 Model 的作用，
 * 从构造方法可以看出，需要传入 ILoginView 和 IModel 实例。
 * 在调用 login 方法时从 ILoginView 中获取 username 和 password 然后并以此为参数调用 userModel
 * 的 login 方法，然后把结果返回给 ILoginView
 *
 */
public class LoginPresenterImpl implements ILoginPresenter {
    private ILoginView mLoginView;
    private IUserModel mUserModel;

    public LoginPresenterImpl(ILoginView loginView, IUserModel userModel) {
        mLoginView = loginView;
        mUserModel = userModel;
    }

    @Override
    public void login() {
        mLoginView.showLoading("登录中");

        mUserModel.login(mLoginView.getUsername(), mLoginView.getPassword(), new Callback() {
            @Override
            public void onSuccess() {
                mLoginView.hideLoading();
                mLoginView.showResult("登录成功");
            }

            @Override
            public void onFail(String msg) {
                mLoginView.hideLoading();
                mLoginView.showResult(msg);
            }
        });
    }
}
