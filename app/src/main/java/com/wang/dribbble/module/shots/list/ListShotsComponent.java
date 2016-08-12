package com.wang.dribbble.module.shots.list;

import com.wang.dribbble.inject.ShotsRepositoryComponent;
import com.wang.dribbble.inject.FragmentScoped;


import dagger.Component;

/**
 * Created by Jack Wang on 2016/8/12.
 */
@FragmentScoped
@Component(dependencies = ShotsRepositoryComponent.class,modules = ListShotsPresenterModule.class)
public interface ListShotsComponent {

    void inject(ListShotsFragment fragment);

}
