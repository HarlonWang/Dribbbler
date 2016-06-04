package com.wang.dribbble;

import com.tumblr.remember.Remember;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class Token {

    public static String getValue(){
        return Remember.getString("access_token","");
    }

}
