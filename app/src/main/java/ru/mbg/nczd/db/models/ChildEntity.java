package ru.mbg.nczd.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Дмитрий on 04.02.2018.
 */

@Entity(tableName = "Children")
public class ChildEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "child_id")
    private long childId;

    @ColumnInfo(name = "user_id")
    private long userId;

    private String firstName;

    private String secondName;

    private String patronymic;

    private String dateBirth;

    private String omc;

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getOmc() {
        return omc;
    }

    public void setOmc(String omc) {
        this.omc = omc;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
