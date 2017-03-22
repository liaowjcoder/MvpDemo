package com.zeal.mvp_loader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.zeal.mvp_loader.presenter.Presenter;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/17 16:04
 * @描述 ${TODO} 
 */


/**
 * BaseActivity 的作用主要就是实现 MvpView 的共性 UI 的操作。
 */
public class BaseActivity<P extends Presenter<V>, V extends MvpView> extends AppCompatActivity
        implements MvpView, LoaderManager.LoaderCallbacks<P> {
    protected ProgressDialog mLodingProgressDialog;
    public final static int BASE_ACTIVITY_LOADER_ID = 100;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLodingProgressDialog = new ProgressDialog(this);

        getSupportLoaderManager().initLoader(BASE_ACTIVITY_LOADER_ID, null, this);
    }

    @Override
    public void showLoading(String msg) {
        mLodingProgressDialog.setMessage(msg);
        if (! mLodingProgressDialog.isShowing()) {
            mLodingProgressDialog.show();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mLodingProgressDialog!=null)
            mLodingProgressDialog.dismiss();//onSaveInstanceState 调用 dismiss 防止内存泄漏
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


    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        Log.e("zeal", "onLoadFinished:"+data);//在这里每次 activity 重启之后的 P 的对象都是同一个。
        mPresenter = data;
        mPresenter.attachView((V) this);
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
