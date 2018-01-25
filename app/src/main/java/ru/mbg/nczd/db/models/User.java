package ru.mbg.nczd.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Дмитрий on 19.01.2018.
 */
@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String login;

    private String omc;

    private String password;

    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOmc() {
        return omc;
    }

    public void setOmc(String omc) {
        this.omc = omc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
