package com.wang.dribbble.module.login;


/**
 * Created by Jack Wang on 2016/6/2.
 */
public interface LoginContract {

    interface View{

        void loginSuccess();

        void loginFailure();

    }

    interface Presenter{

        void loadToken(String code);

    }

}
