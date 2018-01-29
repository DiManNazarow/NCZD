package ru.mbg.nczd.db.models;

import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Дмитрий on 30.01.2018.
 */

public class User extends UserEntity {

    @Relation(parentColumn = "user_id", entityColumn = "reception_id", entity = ReceptionEntity.class)
    private List<Reception> mReceptions;

    public List<Reception> getReceptions() {
        return mReceptions;
    }

    public void setReceptions(List<Reception> receptions) {
        mReceptions = receptions;
    }

    @Ignore
    public boolean isUserHaveReception(){
        return mReceptions != null && !mReceptions.isEmpty();
    }

}
