package com.wang.dribbble.module.shots;

import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.ShotsDataSource;
import com.wang.dribbble.data.source.ShotsRepository;

import java.util.List;

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
    public void loadListShots(boolean forceUpdate) {
        mView.setLoadingIndicator(true);
        if (forceUpdate){
            mShotsRepository.refreshShots();
        }
        mShotsRepository.getListShots(new ShotsDataSource.LoadListShotsCallback() {
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

}
