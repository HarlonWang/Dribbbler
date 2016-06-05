package com.wang.dribbble.api;

import com.wang.dribbble.Injection;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class RetrofitClient {

    private Retrofit retrofit;

    private RetrofitClient(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new DrTokenInterceptor())
                .build();
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(DribbbleService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public DribbbleService getDRService(){
        return retrofit.create(DribbbleService.class);
    }

    public static RetrofitClient getInstance(){
        return ClientInstance.sInstance;
    }

    private static class ClientInstance{
        private static RetrofitClient sInstance=new RetrofitClient();
    }

    private class DrTokenInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            request=request.newBuilder().addHeader("Authorization",new StringBuilder()
                    .append("Bearer ")
                    .append(Injection.provideTokenValue())
                    .toString()).build();
            return chain.proceed(request);
        }
    }
}
