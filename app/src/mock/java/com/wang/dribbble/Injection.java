package com.wang.dribbble;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wang.dribbble.data.source.ShotsRepository;
import com.wang.dribbble.data.source.local.ShotsLocalDataSource;
import com.wang.dribbble.data.source.remote.ShotsRemoteDataSource;

import static com.wang.dribbble.utils.Utils.checkNotNull;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class Injection {

    public static ShotsRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        return ShotsRepository.getInstance(ShotsLocalDataSource.getInstance(context), ShotsRemoteDataSource.getInstance());
    }

    public static String provideTokenValue(){
        return "your access_token for test .";
    }

}
