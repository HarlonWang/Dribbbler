package com.wang.dribbble.module.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wang.dribbble.R;

import java.util.List;

/**
 * Created by Administrator on 2015/3/12.
 */
public abstract class FragmentManagerActivity extends BaseActivity {

    public final String FRAGMENT_TAG_FORMAT = "Fragment_%s";

    public Fragment mCurrentFragment;

    public void addFragment(int index) {

        Fragment fragment;
        String tag = String.format(FRAGMENT_TAG_FORMAT, index + 1);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        List<Fragment> fragments = manager.getFragments();
        if (fragments != null) {
            for (Fragment f : fragments) {
                transaction.hide(f);
            }
        }

        fragment = manager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = createFragment(index);
            transaction.add(R.id.content, fragment, tag);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
        mCurrentFragment=fragment;
    }

    public abstract Fragment createFragment(int index);

}
