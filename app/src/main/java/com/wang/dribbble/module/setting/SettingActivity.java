package com.wang.dribbble.module.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wang.dribbble.R;
import com.wang.dribbble.module.base.BaseActivity;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class SettingActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getFragmentManager().beginTransaction().replace(R.id.content,new DRPreferenceFragment()).commit();
    }
}
