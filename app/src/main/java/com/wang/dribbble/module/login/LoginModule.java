package com.wang.dribbble.module.login;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jack Wang on 2016/8/11.
 */
@Module
public class LoginModule {

    LoginContract.View mView;

    public LoginModule(LoginContract.View view){
        this.mView=view;
    }

    @Provides
    public LoginContract.View provideView(){
        return mView;
    }

}
