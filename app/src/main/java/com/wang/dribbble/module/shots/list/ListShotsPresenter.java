package com.wang.dribbble.module.shots.list;

import com.wang.dribbble.api.RetrofitClient;
import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.ShotsDataSource;
import com.wang.dribbble.data.source.ShotsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class ListShotsPresenter implements ListShotsContract.Presenter{

    private ShotsRepository mShotsRepository;
    private ListShotsContract.View mView;

    public ListShotsPresenter(ShotsRepository shotsRepository,ListShotsContract.View view){
        this.mShotsRepository=shotsRepository;
        this.mView=view;
    }

    @Override
    public void loadListShots(boolean forceUpdate, int filterId) {
        mView.setLoadingIndicator(true);
        if (forceUpdate){
            mShotsRepository.refreshShots();
        }
        mShotsRepository.getListShots(filterId,new ShotsDataSource.LoadListShotsCallback() {
            @Override
            public void onListShotsLoaded(List<Shots> shotsList) {
                mView.setLoadingIndicator(false);
                mView.showListShots(shotsList);
            }

            @Override
            public void onDataNotAvailable() {
                mView.setLoadingIndicator(false);
                mView.showLoadFailed("Load failed ...");
            }
        });
    }

    @Override
    public void loadMoreShots(int page,int filterId) {
        mShotsRepository.getListShotsByPage(page, filterId, new ShotsDataSource.LoadListShotsCallback() {
            @Override
            public void onListShotsLoaded(List<Shots> shotsList) {
                mView.showListShots(shotsList);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showLoadFailed("Load failed ...");
            }
        });
    }
}
