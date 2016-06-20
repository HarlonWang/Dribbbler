package com.wang.dribbble.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.wang.dribbble.R;
import com.wang.dribbble.data.model.User;
import com.wang.dribbble.module.base.FragmentManagerActivity;
import com.wang.dribbble.module.setting.SettingActivity;
import com.wang.dribbble.module.shots.ShotsPagesFragment;
import com.wang.dribbble.module.shots.list.ListShotsFragment;

public class MainActivity extends FragmentManagerActivity implements MainContract.View{

    private static final int LIST_SHOTS=0;

    private AccountHeader headerResult = null;
    private Drawer result = null;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter=new MainPresenter(this);
        setupDrawer(savedInstanceState);
        if (savedInstanceState==null){
            addFragment(LIST_SHOTS);
        }
        mPresenter.loadAccountUser();
    }

    private void setupDrawer(Bundle savedInstanceState) {

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(new ProfileDrawerItem().withName("----").withIdentifier(1))
                .withSelectionListEnabledForSingleProfile(false)
                .withSavedInstance(savedInstanceState)
                .withOnAccountHeaderProfileImageListener(new AccountHeader.OnAccountHeaderProfileImageListener() {
                    @Override
                    public boolean onProfileImageClick(View view, IProfile profile, boolean current) {
                        return false;
                    }

                    @Override
                    public boolean onProfileImageLongClick(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Shots").withIcon(GoogleMaterial.Icon.gmd_spa).withIdentifier(1).withSelectable(false),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Setting").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(2).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier()==2){
                            startActivity(new Intent(getActivity(),SettingActivity.class));
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    public void updateUserProfile(User user) {
        headerResult.updateProfile(new ProfileDrawerItem().withName(user.name).withEmail(user.html_url).withIcon(user.avatar_url).withIdentifier(1));
    }


    @Override
    public Fragment createFragment(int index) {
        switch (index){
            case LIST_SHOTS:
                return ShotsPagesFragment.newInstance();
        }
        return null;
    }
}
