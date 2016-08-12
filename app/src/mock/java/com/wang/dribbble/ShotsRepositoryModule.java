package com.wang.dribbble;

import android.content.Context;

import com.wang.dribbble.data.source.ShotsDataSource;
import com.wang.dribbble.data.source.local.ShotsLocalDataSource;
import com.wang.dribbble.data.source.remote.ShotsRemoteDataSource;
import com.wang.dribbble.inject.Local;
import com.wang.dribbble.inject.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jack Wang on 2016/8/12.
 */
@Module
public class ShotsRepositoryModule {

    @Singleton
    @Provides
    @Local
    public ShotsDataSource provideLocal(Context context){
        return new ShotsLocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    public ShotsDataSource provideRemote(){
        return new ShotsRemoteDataSource();
    }

}
