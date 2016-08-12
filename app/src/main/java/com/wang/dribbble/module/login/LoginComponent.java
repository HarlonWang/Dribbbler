package com.wang.dribbble.module.login;

import dagger.Component;

/**
 * Created by Jack Wang on 2016/8/12.
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
