package com.wang.dribbble.api;

import com.wang.dribbble.data.model.DribbbleToken;
import com.wang.dribbble.data.model.Shots;
import com.wang.dribbble.data.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Single;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public interface DribbbleService {

    String HOST="https://api.dribbble.com/v1/";

    @FormUrlEncoded
    @POST("https://dribbble.com/oauth/token")
    Single<DribbbleToken> getDrToken(@Field("client_id")String client_id,
                                     @Field("client_secret")String client_secret,
                                     @Field("code")String code,
                                     @Field("redirect_uri")String redirect_uri);


    @GET("shots")
    Single<List<Shots>> getShotsList();

    @GET("shots")
    Single<List<Shots>> getShotsList(@QueryMap Map<String,String> map);

    @GET("shots/{id}")
    Single<Shots> getShots(@Path("id")int shotsId);

    @GET("user")
    Single<User> getAuthenticatedUser();
}
