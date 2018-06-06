package de.home24.home24task.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ArticlesDAO {

    //All the required queries required for the functionalities

    @Query("SELECT * FROM Articles")
    List<Articles> getAllArticles();

    @Insert
    void insertAll(Articles... articles);

    @Delete
    public void deleteUsers(Articles... articles);

    @Query("DELETE FROM Articles")
    void delete();
}
