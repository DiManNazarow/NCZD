package ru.mbg.nczd.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.db.models.UserEntity;

/**
 * Created by Дмитрий on 19.01.2018.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(UserEntity user);

    @Update
    int update(UserEntity userEntity);

    @Query("SELECT * FROM UserEntity WHERE UserEntity.login = :login AND UserEntity.password = :password")
    User get(String login, String password);

    @Query("SELECT * FROM UserEntity WHERE UserEntity.id = :id")
    User get(long id);

    @Query("SELECT COUNT(1) FROM UserEntity WHERE UserEntity.login = :login")
    int userExits(String login);

    @Query("SELECT COUNT(1) FROM UserEntity WHERE UserEntity.email = :email")
    int emailExist(String email);

}
