package com.zeal.mvp_login_advance;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zeal.mvp_login_advance.view.MvpView;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 16:04
 * @描述 ${TODO} 
 */


/**
 * BaseActivity 的作用主要就是实现 MvpView 的共性 UI 的操作。
 */
public class BaseActivity extends AppCompatActivity implements MvpView {
    protected ProgressDialog mLodingProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLodingProgressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mLodingProgressDialog!=null)
            mLodingProgressDialog.dismiss();
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
    public void showError(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }


}
