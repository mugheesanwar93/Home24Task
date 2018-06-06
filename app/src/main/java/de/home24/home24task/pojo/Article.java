package de.home24.home24task.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Article {
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("media")
    @Expose
    private ArrayList<Medium> media = null;


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<Medium> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Medium> media) {
        this.media = media;
    }
}
