package com.wang.dribbble.module.main;

import com.wang.dribbble.data.model.User;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public interface MainContract {

    interface View{
        void updateUserProfile(User user);
    }

    interface Presenter{
        void loadAccountUser();
    }

}
