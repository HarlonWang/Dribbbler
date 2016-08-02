package com.wang.dribbble.module.login;

import android.text.TextUtils;

import com.tumblr.remember.Remember;
import com.wang.dribbble.BuildConfig;
import com.wang.dribbble.api.RetrofitClient;
import com.wang.dribbble.data.model.DribbbleToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        Observable<DribbbleToken> drToken = RetrofitClient.getInstance().getDRService().getDrToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code, BuildConfig.REDIRECT_URI);
        drToken
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DribbbleToken>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.loginFailure();
            }

            @Override
            public void onNext(DribbbleToken dribbbleToken) {
                String access_token=dribbbleToken.access_token;
                if (TextUtils.isEmpty(access_token)){
                    mView.loginFailure();
                    return;
                }
                Remember.putString("access_token",access_token);
                Remember.putString("token_type",dribbbleToken.access_token);
                Remember.putString("scope",dribbbleToken.access_token);
                mView.loginSuccess();
            }
        });
    }


}
