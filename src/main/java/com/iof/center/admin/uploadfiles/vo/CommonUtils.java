package com.iof.center.admin.uploadfiles.vo;

import java.util.UUID;

public class CommonUtils {
     
    public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

