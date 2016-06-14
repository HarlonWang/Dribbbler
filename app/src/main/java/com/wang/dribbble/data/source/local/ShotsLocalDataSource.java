package com.wang.dribbble.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.ShotsDataSource;

import java.util.List;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class ShotsLocalDataSource implements ShotsDataSource{

    private static ShotsLocalDataSource INSTANCE;

    private Context mContext;

    private ShotsLocalDataSource(Context context) {
        this.mContext=context;
    }

    @Override
    public void getListShots(int filterId,@NonNull LoadListShotsCallback callback) {
        callback.onDataNotAvailable();
    }

    @Override
    public void getListShotsByPage(int page, int filterId, @NonNull LoadListShotsCallback callback) {

    }

    @Override
    public void deleteAllShots() {

    }

    @Override
    public void refreshShots() {

    }

    @Override
    public void getShots(@NonNull int shotsId, @NonNull GetShotsCallback callback) {

    }

    @Override
    public void saveListShots(List<Shots> shotsList) {

    }

    public static ShotsDataSource getInstance(Context context) {
        if (INSTANCE==null){
            INSTANCE=new ShotsLocalDataSource(context);
        }
        return INSTANCE;
    }
}
