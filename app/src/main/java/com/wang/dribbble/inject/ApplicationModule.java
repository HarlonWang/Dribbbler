package com.wang.dribbble.inject;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jack Wang on 2016/8/12.
 */
@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context){
        mContext=context;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }

}
