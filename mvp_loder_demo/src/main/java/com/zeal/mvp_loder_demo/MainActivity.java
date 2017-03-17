package com.zeal.mvp_loder_demo;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoaderManager loaderManager = getLoaderManager();

        /*
        第一个参数代表当前Loader的ID ,用来区分哪个loader；
        第二个参数代表提供给Loader构造函数的参数，Bundle对象类型 ，可选；
        第三个参数代表LoaderManager.LoaderCallbacks的回调实现 需要我自我实现。

        当 ID 存在则覆用已经存在的 Loader 实例，如果当前 ID 对应的 Loader 不存在，则创建 Loader 实例，并回调 onCreateLoader 方法
         */
        Loader<List<String>> loader = loaderManager.initLoader(0, null, new LoaderManager
                .LoaderCallbacks<List<String>>() {
            //Loader 创建时回调
            @Override
            public Loader<List<String>> onCreateLoader(int id, Bundle args) {
                Log.e("zeal", "-------------------------onCreateLoader id:" + id);
                return new DataLoader(MainActivity.this);
            }

            //loadInBackground 调用完毕之后回调
            @Override
            public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
                Log.e("zeal", "-------------------------onLoadFinished data:" + data);
            }


            @Override
            public void onLoaderReset(Loader<List<String>> loader) {
                Log.e("zeal", "-------------------------onLoaderReset " + loader);
            }
        });


        loader.forceLoad();

    }

    private static class DataLoader extends AsyncTaskLoader<List<String>> {
        private List<String> datas = new ArrayList<>();

        /**
         * Stores away the application context associated with context.
         * Since Loaders can be used across multiple activities it's dangerous to
         * store the context directly; always use {@link #getContext()} to retrieve
         * the Loader's Context, don't use the constructor argument directly.
         * The Context returned by {@link #getContext} is safe to use across
         * Activity instances.
         *
         * @param context used to retrieve the application context.
         */
        public DataLoader(Context context) {
            super(context);
        }

        @Override
        public List<String> loadInBackground() {
            for (int i = 0; i < 100; i++) {
                datas.add("Hello Loader : " + i);
                //SystemClock.sleep(1000);
            }
            return datas;
        }
    }
}
