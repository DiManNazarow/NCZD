package ru.mbg.nczd.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.mbg.nczd.db.dao.ReceptionDao;
import ru.mbg.nczd.db.dao.UserDao;
import ru.mbg.nczd.db.models.ReceptionEntity;
import ru.mbg.nczd.db.models.UserEntity;

/**
 * Created by Дмитрий on 19.01.2018.
 */
@Database(entities = {UserEntity.class, ReceptionEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    public abstract ReceptionDao getReceptionDao();

}
