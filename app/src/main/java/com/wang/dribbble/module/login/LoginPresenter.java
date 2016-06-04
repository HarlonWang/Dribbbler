package com.wang.dribbble.module.login;

import android.text.TextUtils;

import com.tumblr.remember.Remember;
import com.wang.dribbble.BuildConfig;
import com.wang.dribbble.api.RetrofitClient;
import com.wang.dribbble.data.model.DribbbleToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view){
        this.mView=view;
    }

    @Override
    public void loadToken(String code) {
        final Call<DribbbleToken> tokenCall= RetrofitClient.getInstance().getDRService().getDrToken(BuildConfig.CLIENT_ID,BuildConfig.CLIENT_SECRET,code,BuildConfig.REDIRECT_URI);
        tokenCall.enqueue(new Callback<DribbbleToken>() {
            @Override
            public void onResponse(Call<DribbbleToken> call, Response<DribbbleToken> response) {
                String access_token=response.body().access_token;
                if (TextUtils.isEmpty(access_token)){
                    mView.loginFailure();
                    return;
                }
                Remember.putString("access_token",access_token);
                Remember.putString("token_type",response.body().access_token);
                Remember.putString("scope",response.body().access_token);
                mView.loginSuccess();
            }

            @Override
            public void onFailure(Call<DribbbleToken> call, Throwable t) {
                mView.loginFailure();
            }
        });
    }


}
