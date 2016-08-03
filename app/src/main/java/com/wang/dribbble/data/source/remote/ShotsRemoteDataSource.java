package com.wang.dribbble.data.source.remote;

import android.support.annotation.NonNull;

import com.wang.dribbble.api.DribbbleService;
import com.wang.dribbble.api.RetrofitClient;
import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.ShotsDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class ShotsRemoteDataSource implements ShotsDataSource{

    public static final int FILTER_POPULAR=0;

    public static final int FILTER_RECENT=1;

    public static final int FILTER_DEBUTS=2;

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
    public void getListShots(int filterId,@NonNull final LoadListShotsCallback callback) {
        Map<String,String> map=new HashMap<>();
        switch (filterId){
            case FILTER_POPULAR:
                map.put("sort","popular");
                break;
            case FILTER_RECENT:
                map.put("sort","recent");
                break;
            case FILTER_DEBUTS:
                map.put("list","debuts");
                break;
        }
        mService.getShotsList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Shots>>() {
                    @Override
                    public void onSuccess(List<Shots> value) {
                        callback.onListShotsLoaded(value);
                    }

                    @Override
                    public void onError(Throwable error) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getListShotsByPage(int page, int filterId, @NonNull final LoadListShotsCallback callback) {
        Map<String,String> map=new HashMap<>();
        switch (filterId){
            case 0:
                map.put("sort","popular");
                break;
            case 1:
                map.put("sort","recent");
                break;
            case 2:
                map.put("list","debuts");
                break;
        }
        map.put("page",String.valueOf(page));
        map.put("per_page","12");
        mService.getShotsList(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Shots>>() {
                    @Override
                    public void onSuccess(List<Shots> shotsList) {
                        callback.onListShotsLoaded(shotsList);
                    }

                    @Override
                    public void onError(Throwable error) {
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
        mService.getShots(shotsId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<Shots>() {
                    @Override
                    public void onSuccess(Shots shots) {
                        callback.onShotsLoaded(shots);
                    }

                    @Override
                    public void onError(Throwable error) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void saveListShots(List<Shots> shotsList) {

    }
}
