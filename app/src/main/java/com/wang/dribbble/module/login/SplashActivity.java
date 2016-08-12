package com.wang.dribbble.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tumblr.remember.Remember;
import com.wang.dribbble.R;
import com.wang.dribbble.module.base.BaseActivity;
import com.wang.dribbble.module.main.MainActivity;

import butterknife.BindView;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.mSplashPhoto)
    ImageView mSplashPhoto;
    @BindView(R.id.mLoginBut)
    Button mLoginBut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showSplashPhoto();

        checkToken();
    }

    private void checkToken() {
        String token = Remember.getString("access_token", "");
        if (TextUtils.isEmpty(token)) {
            mLoginBut.setVisibility(View.VISIBLE);
            return;
        }
    }

    private void showSplashPhoto() {
        ObjectAnimator alphaAnim=ObjectAnimator.ofFloat(mSplashPhoto,"alpha",0,1);
        alphaAnim.setDuration(3000);
        alphaAnim.start();
        alphaAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(getActivity(),MainActivity.class));
                finish();
            }
        });
    }

    public void showLoginUi(View view) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

}
