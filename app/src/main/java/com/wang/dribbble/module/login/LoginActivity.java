package com.wang.dribbble.module.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wang.dribbble.BuildConfig;
import com.wang.dribbble.R;
import com.wang.dribbble.module.base.BaseActivity;
import com.wang.dribbble.module.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";

    public static final String AUTHORIZE_URL="https://dribbble.com/oauth/authorize?client_id=%s";

    @BindView(R.id.mWebView)
    WebView mWebView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dribbble);
        DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
        setupWebView();
    }

    private void setupWebView() {
        mWebView.clearCache(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new OAuthWebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == progressBar.getVisibility()) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebView.loadUrl(String.format(AUTHORIZE_URL,BuildConfig.CLIENT_ID));
    }

    @Override
    public void loginSuccess() {
        Intent intent=new Intent(getActivity(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        Toast.makeText(getActivity(),"sign in failed , please try again .",Toast.LENGTH_SHORT).show();
        finish();
    }

    class OAuthWebViewClient extends WebViewClient {
        private int index = 0;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.contains(BuildConfig.REDIRECT_URI+"?code=") && index == 0) {
                index++;
                Uri uri = Uri.parse(url);
                String code = uri.getQueryParameter("code");
                if (!TextUtils.isEmpty(code)){
                    mPresenter.loadToken(code);
                }
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

}
