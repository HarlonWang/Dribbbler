package com.wang.dribbble.shots.list;

import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.source.ShotsDataSource;
import com.wang.dribbble.data.source.ShotsRepository;
import com.wang.dribbble.module.shots.list.ListShotsContract;
import com.wang.dribbble.module.shots.list.ListShotsPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Jack Wang on 2016/6/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListShotsPresenterTest {

    @Mock
    ShotsRepository shotsRepository;

    @InjectMocks
    ListShotsPresenter listShotsPresenter;

    @Mock
    ListShotsContract.View view;

    @Captor
    ArgumentCaptor<ShotsDataSource.LoadListShotsCallback> captor;

    @Captor
    ArgumentCaptor<List> listCaptor;

    @Test
    public void getListShotsSuccessful(){
        List<Shots> shotsList=new ArrayList<>();
        shotsList.add(new Shots());
        listShotsPresenter.loadListShots(true,0);
        verify(view).setLoadingIndicator(true);
        verify(shotsRepository).getListShots(eq(0),captor.capture());
        captor.getValue().onListShotsLoaded(shotsList);
        verify(view).setLoadingIndicator(false);
        verify(view).showListShots(listCaptor.capture());
        assertEquals(1,listCaptor.getValue().size());
    }

    @Test
    public void getListShotsDataNotAvailable(){
        listShotsPresenter.loadListShots(true,0);
        verify(view).setLoadingIndicator(true);
        verify(shotsRepository).getListShots(eq(0),captor.capture());
        captor.getValue().onDataNotAvailable();
        verify(view).setLoadingIndicator(false);
        verify(view).showLoadFailed(eq("Load failed ..."));
    }

}
