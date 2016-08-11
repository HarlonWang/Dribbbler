package com.wang.dribbble;

import android.content.Context;

import com.wang.dribbble.data.source.Local;
import com.wang.dribbble.data.source.Remote;
import com.wang.dribbble.data.source.ShotsDataSource;
import com.wang.dribbble.data.source.local.ShotsLocalDataSource;
import com.wang.dribbble.data.source.remote.ShotsRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jack Wang on 2016/8/11.
 */
@Module
public class ShotsRepositoryModule {

    @Singleton
    @Provides
    @Local
    public ShotsDataSource provideLocalDataSource(Context context){
        return new ShotsLocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    public ShotsDataSource provideRemoteDataSource(){
        return new ShotsRemoteDataSource();
    }

}
