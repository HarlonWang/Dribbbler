package com.wang.dribbble;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jack Wang on 2016/8/11.
 */
@Module
public final class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}