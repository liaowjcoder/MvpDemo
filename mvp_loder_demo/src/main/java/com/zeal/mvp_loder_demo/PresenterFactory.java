package com.zeal.mvp_loder_demo;

public interface PresenterFactory<T extends Presenter> {
    T create();
}