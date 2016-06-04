package com.wang.dribbble.module.shots.list;

import com.wang.dribbble.data.model.Shots;

import java.util.List;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public interface ListShotsContract {

    interface View{
        void setLoadingIndicator(boolean active);
        void showListShots(List<Shots> shotsList);
        void showLoadFailed(String message);
    }

    interface Presenter{
        void loadListShots(boolean forceUpdate);
    }

}
