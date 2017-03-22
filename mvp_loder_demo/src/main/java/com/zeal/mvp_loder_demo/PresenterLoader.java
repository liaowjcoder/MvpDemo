package com.zeal.mvp_loder_demo;

import android.content.Context;
import android.content.Loader;
import android.util.Log;

/**
 * @作者 廖伟健
 * @创建时间 2017/3/21 15:56
 * @描述 ${TODO} 
 */

public class PresenterLoader<T extends Presenter> extends Loader<T> {
    private T presenter;
    private PresenterFactory<T> factory;


    public PresenterLoader(Context context,PresenterFactory factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e("zeal", "onStartLoading");
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.e("zeal", "onForceLoad");
        super.onForceLoad();
        presenter = factory.create();
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        Log.e("zeal", "onReset");
        super.onReset();
        presenter = null;
    }
}
