package com.zeal.mvp_loader.presenter;

public interface PresenterFactory<T extends Presenter> {
    T create();
}