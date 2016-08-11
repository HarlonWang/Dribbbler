package com.wang.dribbble;

import com.tumblr.remember.Remember;

/**
 * Created by Jack Wang on 2016/6/3.
 */
public class TokenProvide {

    public static String provideTokenValue(){
        return Remember.getString("access_token","");
    }

}
