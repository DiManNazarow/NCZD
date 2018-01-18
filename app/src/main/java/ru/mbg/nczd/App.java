package ru.mbg.nczd;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.mbg.nczd.db.AppDatabase;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public class App extends Application {

    private AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppDatabase = createAppDataBase();
    }

    private AppDatabase createAppDataBase(){
        return Room.databaseBuilder(this, AppDatabase.class, "AppDataBase").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public AppDatabase getAppDatabase(){
        return mAppDatabase;
    }

}
