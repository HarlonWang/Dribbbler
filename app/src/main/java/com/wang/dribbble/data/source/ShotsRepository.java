package com.wang.dribbble.data.source;

import android.support.annotation.NonNull;

import com.wang.dribbble.data.model.Shots;

import java.util.List;
import static com.wang.dribbble.utils.Utils.checkNotNull;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class ShotsRepository implements ShotsDataSource{

    private static ShotsRepository INSTANCE = null;

    private final ShotsDataSource mShotsLocalDataSource;

    private final ShotsDataSource mShotsRemoteDataSource;

    List<Shots> mCachedShots;

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
    public void getListShots(@NonNull final LoadListShotsCallback callback) {
        checkNotNull(callback);
        if (mCachedShots!=null&&!mCacheIsDirty){
            callback.onListShotsLoaded(mCachedShots);
            return;
        }
        if (mCacheIsDirty){
            getListShotsFromRemote(callback);
        }else {
            mShotsLocalDataSource.getListShots(new LoadListShotsCallback() {
                @Override
                public void onListShotsLoaded(List<Shots> shotsList) {
                    mCachedShots=shotsList;
                    callback.onListShotsLoaded(mCachedShots);
                }

                @Override
                public void onDataNotAvailable() {
                    getListShotsFromRemote(callback);
                }
            });
        }
    }

    private void getListShotsFromRemote(final LoadListShotsCallback callback){
        mShotsRemoteDataSource.getListShots(new LoadListShotsCallback() {
            @Override
            public void onListShotsLoaded(List<Shots> shotsList) {
                mCachedShots=shotsList;
                mCacheIsDirty=false;
                mShotsLocalDataSource.deleteAllShots();
                mShotsLocalDataSource.saveListShots(mCachedShots);
                callback.onListShotsLoaded(mCachedShots);
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
        for(Shots shots:mCachedShots){
            if (shots.id==shotsId){
                callback.onShotsLoaded(shots);
                break;
            }
        }
    }

    @Override
    public void saveListShots(List<Shots> shotsList) {

    }
}
