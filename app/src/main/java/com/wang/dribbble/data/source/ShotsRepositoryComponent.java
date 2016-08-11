package com.wang.dribbble.data.source;

import com.wang.dribbble.ApplicationModule;
import com.wang.dribbble.ShotsRepositoryModule;
import com.wang.dribbble.module.shots.list.ListShotsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jack Wang on 2016/8/11.
 */
@Singleton
@Component(modules = {ShotsRepositoryModule.class,ApplicationModule.class})
public interface ShotsRepositoryComponent {

    void inject(ListShotsFragment fragment);

}
