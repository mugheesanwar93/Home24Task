package de.home24.home24task.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Articles {

    public Articles(String sku, String title, String image, Boolean isLiked) {
        this.sku = sku;
        this.title = title;
        this.image = image;
        this.isLiked = isLiked;
    }

    @ColumnInfo(name = "sku")
    String sku;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "image")
    String image;

    @ColumnInfo(name = "is_liked")
    Boolean isLiked;


    @PrimaryKey(autoGenerate = true)
    public Integer id;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
