package com.wang.dribbble.module.shots.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wang.dribbble.ApplicationModule;
import com.wang.dribbble.R;
import com.wang.dribbble.ShotsRepositoryModule;
import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.ShotsDataSource;
import com.wang.dribbble.data.source.ShotsRepository;
import com.wang.dribbble.module.base.BaseActivity;
import com.wang.dribbble.utils.ImageSize;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class ShotsDetailActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.author)
    TextView author;

/*
    @Inject
    ShotsRepository shotsRepository;
*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);

        int shotsId = getIntent().getIntExtra("shots_id",0);

/*        DaggerShotsRepositoryComponent
                .builder()
                .applicationModule(new ApplicationModule(getActivity().getApplicationContext()))
                .shotsRepositoryModule(new ShotsRepositoryModule())
                .build()
                .inject(this);*/

        shotsRepository.getShots(shotsId, new ShotsDataSource.GetShotsCallback() {
            @Override
            public void onShotsLoaded(Shots shots) {
                collapsingToolbar.setTitle(shots.title);
                description.setText(Html.fromHtml(shots.description==null?"":shots.description));
                author.setText(shots.user.name);
                Glide.with(getActivity())
                        .load(!TextUtils.isEmpty(shots.images.hidpi) ? shots.images.hidpi : shots.images.normal)
                        .placeholder(R.color.placeholder)
                        .override(ImageSize.NORMAL[0], ImageSize.NORMAL[1])
                        .into(image);
            }

            @Override
            public void onDataNotAvailable() {
                Toast.makeText(getActivity(),"Load error ...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
