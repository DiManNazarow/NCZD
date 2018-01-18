package ru.mbg.nczd.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import ru.mbg.nczd.db.models.User;

/**
 * Created by Дмитрий on 19.01.2018.
 */
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

}
