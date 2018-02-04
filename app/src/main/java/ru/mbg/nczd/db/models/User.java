package ru.mbg.nczd.db.models;

import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import java.util.List;

import ru.mbg.nczd.utils.AppTextUtils;

/**
 * Created by Дмитрий on 30.01.2018.
 */

public class User extends UserEntity {

    @Relation(parentColumn = "id", entityColumn = "user_id", entity = ReceptionEntity.class)
    private List<Reception> mReceptions;

    @Relation(parentColumn = "id", entityColumn = "user_id", entity = ChildEntity.class)
    private List<Child> mChildren;

    public List<Reception> getReceptions() {
        return mReceptions;
    }

    public void setReceptions(List<Reception> receptions) {
        mReceptions = receptions;
    }

    public List<Child> getChildren() {
        return mChildren;
    }

    public void setChildren(List<Child> children) {
        mChildren = children;
    }

    @Ignore
    public boolean isUserHaveReception(){
        return mReceptions != null && !mReceptions.isEmpty();
    }

    @Ignore
    public boolean isUserHaveChildren(){
        return mChildren != null && !mChildren.isEmpty();
    }

    public boolean isUserHaveAllInfo(){
        return !AppTextUtils.isEmpty(firstName) && !AppTextUtils.isEmpty(secondName)
                && !AppTextUtils.isEmpty(patronymic) && !AppTextUtils.isEmpty(dateBirth)
                && !AppTextUtils.isEmpty(number) && !AppTextUtils.isEmpty(omc);
    }

}
