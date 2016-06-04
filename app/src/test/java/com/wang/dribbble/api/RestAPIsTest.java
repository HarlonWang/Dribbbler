package com.wang.dribbble.api;

import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.model.User;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class RestAPIsTest {


    @Test
    public void shotsTest() throws IOException {
        Call<List<Shots>> shotsList = RetrofitClient.getInstance().getDRService().getShotsList();
        Response<List<Shots>> execute = shotsList.execute();
        assertEquals(12,execute.body().size());
    }

    @Test
    public void userTest() throws IOException {
        Call<User> authenticatedUser = RetrofitClient.getInstance().getDRService().getAuthenticatedUser();
        Response<User> execute = authenticatedUser.execute();
        assertEquals("Jack Wang",execute.body().name);
    }

}
