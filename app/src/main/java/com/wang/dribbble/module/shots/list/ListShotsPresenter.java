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
        Call<List<Shots>> shotsList = RetrofitClient.getInstance().getDRService().getShotsList(map);
        shotsList.enqueue(new Callback<List<Shots>>() {
            @Override
            public void onResponse(Call<List<Shots>> call, Response<List<Shots>> response) {
                mView.showListShots(response.body());
            }

            @Override
            public void onFailure(Call<List<Shots>> call, Throwable t) {
                mView.showLoadFailed("Load failed ...");
            }
        });
    }
}
