package com.zeal.mvp_loader.presenter.impl;


import com.zeal.mvp_loader.Callback;
import com.zeal.mvp_loader.ILoginView;
import com.zeal.mvp_loader.model.IUserModel;
import com.zeal.mvp_loader.presenter.BasePresenter;
import com.zeal.mvp_loader.presenter.ILoginPresenter;

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
public class LoginPresenterImpl extends BasePresenter<ILoginView> implements ILoginPresenter {
    private IUserModel mUserModel;

    public LoginPresenterImpl(IUserModel userModel) {
        mUserModel = userModel;
    }

    @Override
    public void login() {
        //检测当前 Presenter 是否与 View 建立连接
        //这样就可以屏蔽空指针问题。
        checkViewAttached();
        getMvpView().showLoading("登录中");

        mUserModel.login(getMvpView().getUsername(), getMvpView().getPassword(), new Callback() {
            @Override
            public void onSuccess() {
                //先调用checkViewAttached()确保View存在才往下走。
                if (isViewAttached()) {
                    getMvpView().hideLoading();
                    getMvpView().showResult("登录成功");
                }
            }

            @Override
            public void onFail(String msg) {
                //先调用checkViewAttached()确保View存在才往下走。
                if (isViewAttached()) {
                    getMvpView().hideLoading();
                    getMvpView().showResult(msg);
                }
            }
        });
    }
}
