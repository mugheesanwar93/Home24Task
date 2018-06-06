package de.home24.home24task.utils;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import de.home24.home24task.db.AppDatabase;
import de.home24.home24task.networks.NetworkManager;


public class ApplicationController extends Application {

    private static ApplicationController sInstance;
    private static AppDatabase db;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getInstance(this);
        sInstance = this;

        db = Room.databaseBuilder(this, AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build(); //creating the database instance


        db.eventDAO().delete();
    }

    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }

    public static AppDatabase getDatabaseObj() {
        return db;
    }
}
