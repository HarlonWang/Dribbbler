package com.wang.dribbble.data.source;

import android.support.annotation.NonNull;

import com.wang.dribbble.data.model.Shots;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wang.dribbble.utils.Utils.checkNotNull;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class ShotsRepository implements ShotsDataSource{

    private static ShotsRepository INSTANCE = null;

    private final ShotsDataSource mShotsLocalDataSource;

    private final ShotsDataSource mShotsRemoteDataSource;

    Map<Integer,List<Shots>> mCachedShots=new HashMap<>();

    boolean mCacheIsDirty = false;

    private ShotsRepository(@NonNull ShotsDataSource shotsLocalDataSource,
                           @NonNull ShotsDataSource shotsRemoteDataSource){
        mShotsLocalDataSource=shotsLocalDataSource;
        mShotsRemoteDataSource=shotsRemoteDataSource;
    }

    public static ShotsRepository getInstance(ShotsDataSource shotsLocalDataSource,
                                              ShotsDataSource shotsRemoteDataSource){
        if (INSTANCE==null){
            INSTANCE=new ShotsRepository(shotsLocalDataSource,shotsRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void getListShots(final int filterId, @NonNull final LoadListShotsCallback callback) {
        checkNotNull(callback);
        if (mCachedShots.get(filterId)!=null&&!mCacheIsDirty){
            callback.onListShotsLoaded(mCachedShots.get(filterId));
            return;
        }
        if (mCacheIsDirty){
            getListShotsFromRemote(filterId,callback);
        }else {
            mShotsLocalDataSource.getListShots(filterId,new LoadListShotsCallback() {
                @Override
                public void onListShotsLoaded(List<Shots> shotsList) {
                    mCachedShots.put(filterId,shotsList);
                    callback.onListShotsLoaded(shotsList);
                }

                @Override
                public void onDataNotAvailable() {
                    getListShotsFromRemote(filterId,callback);
                }
            });
        }
    }

    private void getListShotsFromRemote(final int filterId, final LoadListShotsCallback callback){
        mShotsRemoteDataSource.getListShots(filterId,new LoadListShotsCallback() {
            @Override
            public void onListShotsLoaded(List<Shots> shotsList) {
                mCachedShots.put(filterId,shotsList);
                mCacheIsDirty=false;
                mShotsLocalDataSource.deleteAllShots();
                mShotsLocalDataSource.saveListShots(shotsList);
                callback.onListShotsLoaded(shotsList);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void deleteAllShots() {

    }

    @Override
    public void refreshShots() {
        mCacheIsDirty=true;
    }

    @Override
    public void getShots(@NonNull int shotsId, @NonNull GetShotsCallback callback) {
        if (mCachedShots==null){
            mShotsRemoteDataSource.getShots(shotsId, callback);
        }
        for (int i = 0; i < mCachedShots.size(); i++) {
            for(Shots shots:mCachedShots.get(i)){
                if (shots.id==shotsId){
                    callback.onShotsLoaded(shots);
                    break;
                }
            }
        }
    }

    @Override
    public void saveListShots(List<Shots> shotsList) {

    }





}
