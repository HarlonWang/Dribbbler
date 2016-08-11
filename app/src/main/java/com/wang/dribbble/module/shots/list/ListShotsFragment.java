package com.wang.dribbble.module.shots.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wang.dribbble.ApplicationModule;
import com.wang.dribbble.R;
import com.wang.dribbble.ShotsRepositoryModule;
import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.DaggerShotsRepositoryComponent;
import com.wang.dribbble.data.source.ShotsRepository;
import com.wang.dribbble.module.base.BaseFragment;
import com.wang.dribbble.module.shots.detail.ShotsDetailActivity;
import com.wang.dribbble.utils.ImageSize;
import com.wang.dribbble.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class ListShotsFragment extends BaseFragment implements ListShotsContract.View {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    ListShotsAdapter mAdapter;

    @BindView(R.id.pull_to_refresh)
    SwipeRefreshLayout pullToRefresh;

    public RecyclerView.RecycledViewPool mPool;

    private int filterId;

    @Inject
    ListShotsPresenter mPresenter;

    @Inject
    ShotsRepository shotsRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter=new ListShotsAdapter(getActivity(),new ArrayList<Shots>(0));

        DaggerShotsRepositoryComponent
                .builder()
                .applicationModule(new ApplicationModule(getActivity().getApplicationContext()))
                .shotsRepositoryModule(new ShotsRepositoryModule())
                .build()
                .inject(this);

        /*DaggerListShotsComponent
                .builder()
                .listShotsModule(new ListShotsModule(shotsRepository,this))
                .build()
                .inject(this);*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_shots, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        pullToRefresh.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadListShots(true,filterId);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments()!=null){
            filterId=getArguments().getInt("filterId",0);
        }
        mPresenter.loadListShots(false,filterId);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setRecycleChildrenOnDetach(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (mPool!=null){
            recyclerView.getRecyclerView().setRecycledViewPool(mPool);
        }
        recyclerView.getRecyclerView().setHasFixedSize(true);
        recyclerView.getRecyclerView().setClipToPadding(false);
        recyclerView.getRecyclerView().setPadding(0, Utils.dp2px(getActivity(),16),0,Utils.dp2px(getActivity(),16));
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Shots item = mAdapter.getItem(position);
                Intent intent=new Intent(getActivity(), ShotsDetailActivity.class);
                intent.putExtra("shots_id",item.id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        mAdapter.setMore(R.layout.view_loading, new RecyclerArrayAdapter.OnLoadMoreListener() {

            @Override
            public void onLoadMore() {
                mPresenter.loadListShotsByPage(filterId);
            }
        });
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        pullToRefresh.post(new Runnable() {
            @Override
            public void run() {
                pullToRefresh.setRefreshing(active);
            }
        });
    }

    @Override
    public void showListShots(List<Shots> shotsList) {
        mAdapter.clear();
        mAdapter.addAll(shotsList);
    }

    @Override
    public void showListShotsFromPage(List<Shots> shotsList) {
        mAdapter.addAll(shotsList);
    }

    @Override
    public void showLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public static ListShotsFragment newInstance(int filterId) {
        ListShotsFragment fragment=new ListShotsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("filterId",filterId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static class ListShotsAdapter extends RecyclerArrayAdapter<Shots> {


        public ListShotsAdapter(Context context, List<Shots> objects) {
            super(context, objects);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            return new ListShotsViewHolder(inflater.inflate(R.layout.item_shots, parent, false));
        }

        public class ListShotsViewHolder extends BaseViewHolder<Shots>{

            @BindView(R.id.image)
            public ImageView photo;
            @BindView(R.id.title)
            public TextView title;
            @BindView(R.id.description)
            public TextView description;

            public ListShotsViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }

            @Override
            public void setData(Shots item) {
                Context context=photo.getContext();
                title.setText(item.title);
                description.setText(Html.fromHtml(item.description==null?"":item.description));
                Glide.with(context)
                        .load(item.images.normal)
                        .placeholder(R.color.placeholder)
                        .override(ImageSize.NORMAL[0], ImageSize.NORMAL[1])
                        .into(photo);
            }
        }
    }



}
