package com.wang.dribbble.data.source.remote;

import android.support.annotation.NonNull;

import com.wang.dribbble.api.DribbbleService;
import com.wang.dribbble.api.RetrofitClient;
import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.ShotsDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class ShotsRemoteDataSource implements ShotsDataSource{

    DribbbleService mService;

    private static ShotsDataSource INSTANCE;

    private ShotsRemoteDataSource(){
        mService=RetrofitClient.getInstance().getDRService();
    }

    public static ShotsDataSource getInstance() {
        if (INSTANCE==null){
            INSTANCE=new ShotsRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getListShots(@NonNull final LoadListShotsCallback callback) {
        Call<List<Shots>> shotsList = mService.getShotsList();
        shotsList.enqueue(new Callback<List<Shots>>() {
            @Override
            public void onResponse(Call<List<Shots>> call, Response<List<Shots>> response) {
                callback.onListShotsLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Shots>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteAllShots() {

    }

    @Override
    public void refreshShots() {

    }

    @Override
    public void getShots(@NonNull int shotsId, @NonNull final GetShotsCallback callback) {
        Call<Shots> shots = mService.getShots(shotsId);
        shots.enqueue(new Callback<Shots>() {
            @Override
            public void onResponse(Call<Shots> call, Response<Shots> response) {
                callback.onShotsLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Shots> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveListShots(List<Shots> shotsList) {

    }
}
