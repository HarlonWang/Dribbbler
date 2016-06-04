package com.wang.dribbble.module.setting;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;

import com.wang.dribbble.R;
import com.wang.dribbble.utils.Utils;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class DRPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        findPreference("about").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("About")
                        .setMessage("Author:Jack Wang\n\nGithub:Https://github.com/81813780")
                        .setPositiveButton("ok",null).show();
                return false;
            }
        });
        findPreference("version").setSummary(Utils.getVersion(getActivity()));
    }
}
