package com.wang.dribbble.data.source;

import com.wang.dribbble.data.model.Shots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Jack Wang on 2016/6/17.
 */
public class ShotsRepositoryTest {

    @Mock
    ShotsDataSource shotsRemoteDataSource;

    @Mock
    ShotsDataSource shotsLocalDataSource;

    ShotsRepository shotsRepository;

    @Mock
    ShotsDataSource.LoadListShotsCallback loadListShotsCallback;

    @Captor
    ArgumentCaptor<ShotsDataSource.LoadListShotsCallback> loadListShotsCallbackArgumentCaptor;

    @Captor
    ArgumentCaptor<List> listArgumentCaptor;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        shotsRepository=new ShotsRepository(shotsLocalDataSource,shotsRemoteDataSource);
    }

    @Test
    public void getListShotsFromRemote(){
        shotsRepository.refreshShots();
        shotsRepository.getListShots(0,loadListShotsCallback);
        verify(shotsRemoteDataSource).getListShots(eq(0),loadListShotsCallbackArgumentCaptor.capture());
        loadListShotsCallbackArgumentCaptor.getValue().onListShotsLoaded(new ArrayList<Shots>());
        verify(shotsLocalDataSource).saveListShots(listArgumentCaptor.capture());
        Assert.assertEquals(0,listArgumentCaptor.getValue().size());
    }

}
