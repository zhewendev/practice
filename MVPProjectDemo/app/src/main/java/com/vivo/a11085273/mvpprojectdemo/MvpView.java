package com.vivo.a11085273.mvpprojectdemo;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void showData(String data);

    void showFailureMessage(String msg);

    void showErrorMessage();
}
