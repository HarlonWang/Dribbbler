package com.wang.dribbble.module.shots;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.wang.dribbble.Injection;
import com.wang.dribbble.R;
import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.module.base.BaseFragment;
import com.wang.dribbble.module.shotsdetail.ShotsDetailActivity;
import com.wang.dribbble.utils.GridMarginDecoration;
import com.wang.dribbble.utils.ImageSize;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class ListShotsFragment extends BaseFragment implements ListShotsContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ListShotsAdapter mAdapter;

    ListShotsPresenter mPresenter;
    @BindView(R.id.pull_to_refresh)
    SwipeRefreshLayout pullToRefresh;

    ShotsItemListener shotsItemListener=new ShotsItemListener() {
        @Override
        public void onShotsClick(Shots shots) {
            Intent intent=new Intent(getActivity(), ShotsDetailActivity.class);
            intent.putExtra("shots_id",shots.id);
            startActivity(intent);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter=new ListShotsAdapter(new ArrayList<Shots>(0),shotsItemListener);
        mPresenter = new ListShotsPresenter(Injection.provideTasksRepository(getActivity().getApplicationContext()), this);
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
        recyclerView.setAdapter(mAdapter);
        pullToRefresh.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadListShots(true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadListShots(false);
    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position % 6) {
                    case 5:
                        return 3;
                    case 3:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.addItemDecoration(new GridMarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        recyclerView.setHasFixedSize(true);
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
        mAdapter.setList(shotsList);
    }

    @Override
    public void showLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public static Fragment newInstance() {
        return new ListShotsFragment();
    }

    public static class ListShotsAdapter extends RecyclerView.Adapter<ListShotsAdapter.ListShotsViewHolder> {

        private List<Shots> shotsList;

        private ShotsItemListener mItemListener;

        public ListShotsAdapter(List<Shots> shotsList,ShotsItemListener itemListener) {
            this.shotsList = shotsList;
            mItemListener=itemListener;
        }

        @Override
        public ListShotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            return new ListShotsViewHolder(inflater.inflate(R.layout.item_shots, parent, false));
        }

        @Override
        public void onBindViewHolder(ListShotsViewHolder holder, int position) {
            Shots item=shotsList.get(position);
            Context context=holder.photo.getContext();
            holder.title.setText(item.title);
            holder.gifFlag.setVisibility(item.animated?View.VISIBLE:View.GONE);
            Glide.with(context)
                    .load(item.images.normal)
                    .placeholder(R.color.placeholder)
                    .override(ImageSize.NORMAL[0], ImageSize.NORMAL[1])
                    .into(holder.photo);
        }

        @Override
        public int getItemCount() {
            return shotsList.size();
        }

        public void setList(List<Shots> shotsList){
            this.shotsList=shotsList;
            notifyDataSetChanged();
        }

        public Shots getItem(int position){
            return shotsList.get(position);
        }

        public class ListShotsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            @BindView(R.id.photo)
            public ImageView photo;
            @BindView(R.id.gifFlag)
            public ImageView gifFlag;
            @BindView(R.id.title)
            public TextView title;

            public ListShotsViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                mItemListener.onShotsClick(getItem(position));
            }
        }
    }


    public interface ShotsItemListener{
        void onShotsClick(Shots shots);
    }


}
