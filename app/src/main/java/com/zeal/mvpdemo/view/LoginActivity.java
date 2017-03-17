package com.zeal.mvpdemo.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zeal.mvpdemo.R;
import com.zeal.mvpdemo.model.impl.UserModelImpl;
import com.zeal.mvpdemo.presenter.impl.LoginPresenterImpl;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 14:31
 * @描述 ${TODO} 
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {
    EditText mEtUserName;
    EditText mEtPassword;
    Button mBtnLogin;

    /**
     * presenter
     */
    ProgressDialog mLodingProgressDialog;
    /**
     * model
     */
    LoginPresenterImpl mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.login();
            }
        });

        mLodingProgressDialog = new ProgressDialog(this);

        //初始化 Presenter
        mLoginPresenter = new LoginPresenterImpl(this, new UserModelImpl());
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
    public void showLoading(String msg) {
        mLodingProgressDialog.setMessage(msg);
        if (! mLodingProgressDialog.isShowing()) {
            mLodingProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mLodingProgressDialog.isShowing()) {
            mLodingProgressDialog.dismiss();
        }
    }

    @Override
    public void showResult(String result) {
        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
    }
}
