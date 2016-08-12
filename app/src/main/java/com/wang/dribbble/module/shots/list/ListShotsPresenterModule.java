package com.wang.dribbble.module.shots.list;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jack Wang on 2016/8/12.
 */
@Module
public class ListShotsPresenterModule {

    ListShotsContract.View view;

    public ListShotsPresenterModule(ListShotsContract.View view){
        this.view=view;
    }

    @Provides
    public ListShotsContract.View provideView(){
        return view;
    }

}
