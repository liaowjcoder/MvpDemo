package com.zeal.mvp_loader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zeal.mvp_loader.model.impl.UserModelImpl;
import com.zeal.mvp_loader.presenter.PresenterFactory;
import com.zeal.mvp_loader.presenter.PresenterLoader;
import com.zeal.mvp_loader.presenter.impl.LoginPresenterImpl;


/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 14:31
 * @描述 ${TODO} 
 */

public class LoginActivity extends BaseActivity<LoginPresenterImpl, ILoginView> implements
        ILoginView {
    EditText mEtUserName;
    EditText mEtPassword;
    Button mBtnLogin;
    TextView mTvState;

    //LoginPresenterImpl mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTvState = (TextView) findViewById(R.id.state);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvState.setText("正在登录中...");
                mPresenter.login();
            }
        });

        mLodingProgressDialog = new ProgressDialog(this);

        //初始化 Presenter
        //mLoginPresenter = new LoginPresenterImpl(new UserModelImpl());
        //mLoginPresenter.attachView(this);//这里与View建立连接
    }

    @Override
    public String getUsername() {
        return mEtUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }


    @Override
    public void showResult(String result) {
        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
        mTvState.setText(result);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();//view 与 Presenter 断开连接
        super.onDestroy();
    }

    //    @Override
    //    public Loader<LoginPresenterImpl> onCreateLoader(int id, Bundle args) {
    //        return new PresenterLoader<LoginPresenterImpl>(this, new PresenterFactory() {
    //            @Override
    //            public LoginPresenterImpl create() {
    //                return new LoginPresenterImpl(new UserModelImpl());
    //            }
    //        });
    //    }



    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoader(this, new PresenterFactory<LoginPresenterImpl>() {
            @Override
            public LoginPresenterImpl create() {
                return new LoginPresenterImpl(new UserModelImpl());
            }
        });
    }
}
