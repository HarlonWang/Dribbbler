package com.wang.dribbble.inject;

import com.wang.dribbble.ShotsRepositoryModule;
import com.wang.dribbble.data.source.ShotsRepository;
import com.wang.dribbble.module.shots.detail.ShotsDetailActivity;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Jack Wang on 2016/8/12.
 */
@Singleton
@Component(modules = {ShotsRepositoryModule.class,ApplicationModule.class})
public interface ShotsRepositoryComponent {

    ShotsRepository getShotsRepository();

    void inject(ShotsDetailActivity activity);

}
