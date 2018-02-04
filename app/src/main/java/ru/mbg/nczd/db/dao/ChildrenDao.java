package ru.mbg.nczd.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.mbg.nczd.db.models.Child;
import ru.mbg.nczd.db.models.ChildEntity;

/**
 * Created by Дмитрий on 04.02.2018.
 */

@Dao
public interface ChildrenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(ChildEntity childEntity);

    @Query("SELECT * FROM Children")
    List<Child> getAll();

    @Query("SELECT * FROM Children WHERE Children.child_id = :id")
    Child get(long id);

}
