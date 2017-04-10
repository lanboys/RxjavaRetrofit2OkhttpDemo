package com.bing.lan.retrofit2.api;

import com.bing.lan.utils.HttpUtils;
import com.bing.lan.utils.LogUtil;

import java.util.Map;

/**
 * @author 蓝兵
 * @time 2017/1/12  10:01
 */
public class ApiService {

    /*根API*/
    public static final String BASE_URL = "http://192.168.100.104:8080/GooglePlayServer/";
    // public static final String BASE_URL = "http://192.168.196.2:8080/GooglePlayServer/";
    public static final String IMAGER_URL = BASE_URL + "image?name=";
    private static LogUtil log = LogUtil.getLogUtil(ApiService.class, 1);

    public static String getUrl(String key, Map<String, Object> map) {

        String url = BASE_URL + key + "?";

        url += HttpUtils.getUrlParamsByMap(map);
        log.d("getUrl(): " + url);
        return url;
    }

    //http://192.168.196.2:8080/GooglePlayServer/home?index=0
    //http://192.168.100.102:8080/GooglePlayServer/app?index=0
    //http://192.168.100.102:8080/GooglePlayServer/game?index=0

    public static String getHomeIconUrl(String url) {
        return getImagerUrl(url);
    }

    public static String getAppIconUrl(String url) {
        return getImagerUrl(url);
    }

    public static String getGameIconUrl(String url) {
        return getImagerUrl(url);
    }

    public static String getSubjectPicUrl(String url) {

        return getImagerUrl(url);
    }

    public static String getBannerPicUrl(String url) {

        return getImagerUrl(url);
    }

    public static String getCategoryUrl(String url) {

        return getImagerUrl(url);
    }

    public static String getImagerUrl(String url) {

        return BASE_URL + "image?name=" + url;
    }

    // http://192.168.100.102:8080/GooglePlayServer/home?index=20
    // http://192.168.196.2:8080/GooglePlayServer/image?name=app/com.m520it.www/icon.jpg
}
