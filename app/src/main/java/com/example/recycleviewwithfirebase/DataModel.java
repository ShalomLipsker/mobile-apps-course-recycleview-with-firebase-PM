package com.example.recycleviewwithfirebase;

import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable {

    private String name;
    private String years;
    private String imageUrl;
    private String description;
    private Long _id;

    public DataModel() {
    }

    public DataModel(String name, String years, String imageUrl, String description, Long _id) {
        this.name = name;
        this.years = years;
        this.imageUrl = imageUrl;
        this.description = description;
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
