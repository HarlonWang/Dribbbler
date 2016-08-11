package com.wang.dribbble.data.source;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Jack Wang on 2016/8/11.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Remote {
}
