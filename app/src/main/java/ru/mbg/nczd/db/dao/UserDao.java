package ru.mbg.nczd.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import ru.mbg.nczd.db.models.User;

/**
 * Created by Дмитрий on 19.01.2018.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(User user);

    @Query("SELECT * FROM User WHERE user.login = :login AND user.password = :password")
    User get(String login, String password);

    @Query("SELECT * FROM User WHERE user.id = :id")
    User get(long id);

}
