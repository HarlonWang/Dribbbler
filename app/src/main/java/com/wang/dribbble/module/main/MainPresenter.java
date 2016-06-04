package com.wang.dribbble.module.main;

import com.wang.dribbble.api.RetrofitClient;
import com.wang.dribbble.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Call<User> authenticatedUser = RetrofitClient.getInstance().getDRService().getAuthenticatedUser();
        authenticatedUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mView.updateUserProfile(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
