package com.wang.dribbble.api;

import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.model.User;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import rx.SingleSubscriber;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class RestAPIsTest {


    @Test
    public void shotsTest() throws IOException {
        RetrofitClient.getInstance().getDRService().getShotsList().subscribe(new SingleSubscriber<List<Shots>>() {
            @Override
            public void onSuccess(List<Shots> value) {
                assertEquals(12,value.size());
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    @Test
    public void userTest() throws IOException {
        RetrofitClient.getInstance().getDRService().getAuthenticatedUser().subscribe(new SingleSubscriber<User>() {
            @Override
            public void onSuccess(User value) {
                assertEquals("Jack Wang",value.name);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

}
