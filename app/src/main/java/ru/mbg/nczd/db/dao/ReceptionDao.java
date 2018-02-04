package ru.mbg.nczd.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.mbg.nczd.db.models.Reception;
import ru.mbg.nczd.db.models.ReceptionEntity;

/**
 * Created by Дмитрий on 30.01.2018.
 */

@Dao
public interface ReceptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(ReceptionEntity user);

    @Delete
    void delete(ReceptionEntity reception);

    @Query("SELECT * FROM Reception WHERE Reception.reception_id = :id")
    Reception get(long id);

    @Query("SELECT * FROM Reception")
    List<Reception> getAll();

}
