package com.example.guhao.mytrail;

/**
 * Author: GuHao
 * Date: 12/5/17
 * Time: 1:01 PM
 * Desc:
 */

public class Place {
    private int photoId;
    String name;
    String location;
    String rating;

    public Place(int photoId, String name, String location){
        this.photoId = photoId;
        this.name = name;
        this.location = location;
    }

    public Place() {
        //default
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
