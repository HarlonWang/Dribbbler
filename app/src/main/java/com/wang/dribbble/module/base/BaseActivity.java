package com.wang.dribbble.module.base;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wang.dribbble.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Nullable @BindView(R.id.toolbar)
    public Toolbar toolbar;

    public Activity getActivity(){
        return this;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setupToolbar();
    }

    private void setupToolbar() {
        if (toolbar!=null){
            setSupportActionBar(toolbar);
            ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
