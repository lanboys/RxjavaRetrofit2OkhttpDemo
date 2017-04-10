package com.bing.lan.retrofit2.api;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/1/12  10:48
 */
public class HomeInfoBean {

    public  List<String> picture;
    public  List<ListBean> list;

    public static HomeInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, HomeInfoBean.class);
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HomeInfoBean{" +
                "list=" + list +
                ", picture=" + picture +
                '}';
    }

    public static class ListBean {


        public  int id;
        public  String name;
        public  String packageName;
        public  String iconUrl;
        public  float stars;
        public  long size;
        public  String downloadUrl;
        public  String des;

        @Override
        public String toString() {
            return "ListBean{" +
                    "des='" + des + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", iconUrl='" + iconUrl + '\'' +
                    ", stars=" + stars +
                    ", size=" + size +
                    ", downloadUrl='" + downloadUrl + '\'' +
                    '}';
        }

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public float getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public long getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
