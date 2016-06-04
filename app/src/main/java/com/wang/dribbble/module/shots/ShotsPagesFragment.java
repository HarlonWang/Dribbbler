package com.wang.dribbble.module.shots;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.dribbble.R;
import com.wang.dribbble.module.base.BaseFragment;
import com.wang.dribbble.module.shots.list.ListShotsFragment;

import butterknife.BindView;

/**
 * Created by Jack Wang on 2016/6/4.
 */
public class ShotsPagesFragment extends BaseFragment {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static ShotsPagesFragment newInstance() {
        return new ShotsPagesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shots_pages, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            private final String[] CATEGORIES={"Popular","Recent","Debuts"};

            @Override
            public Fragment getItem(int position) {
                return ListShotsFragment.newInstance();
            }

            @Override
            public int getCount() {
                return CATEGORIES.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return CATEGORIES[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.color_99999),getResources().getColor(R.color.colorPrimary));
    }


}
