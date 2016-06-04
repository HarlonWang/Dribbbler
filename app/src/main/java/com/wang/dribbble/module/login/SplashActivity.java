package com.wang.dribbble.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tumblr.remember.Remember;
import com.wang.dribbble.R;
import com.wang.dribbble.module.base.BaseActivity;
import com.wang.dribbble.module.main.MainActivity;
import com.wang.dribbble.utils.ImageSize;

import butterknife.BindView;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class SplashActivity extends BaseActivity {

    public static final String SPLASH_PHOTO="https://unsplash.it/480/800/?random";

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
        new Handler().postDelayed(new Runnable(){

            public void run() {
                startActivity(new Intent(getActivity(),MainActivity.class));
                finish();
            }

        }, 3500);
    }

    private void showSplashPhoto() {
        Glide.with(getActivity())
                .load(SPLASH_PHOTO)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.color.placeholder)
                .override(ImageSize.FULL[0], ImageSize.FULL[1])
                .into(mSplashPhoto);
    }

    public void showLoginUi(View view) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

}
