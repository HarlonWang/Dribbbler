package com.wang.dribbble.module.main;

import com.wang.dribbble.api.RetrofitClient;
import com.wang.dribbble.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class MainPresenter implements MainContract.Presenter{

    private MainContract.View mView;

    public MainPresenter(MainContract.View view){
        mView=view;
    }

    @Override
    public void loadAccountUser() {
        RetrofitClient
                .getInstance()
                .getDRService()
                .getAuthenticatedUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<User>() {
                    @Override
                    public void onSuccess(User user) {
                        mView.updateUserProfile(user);
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });
    }
}
