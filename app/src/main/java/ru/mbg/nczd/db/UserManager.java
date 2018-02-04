package ru.mbg.nczd.db;

import ru.mbg.nczd.App;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.db.models.UserEntity;

/**
 * Created by Дмитрий on 30.01.2018.
 */

public class UserManager {

    private static UserManager sUserManager;

    private long mUserId = -1;

    private UserManager(){}

    public synchronized static UserManager instance(){
        if (sUserManager == null){
            sUserManager = new UserManager();
        }
        return sUserManager;
    }

    public User getUser() {
        return App.getAppDatabase().getUserDao().get(mUserId);
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public boolean isUserAuth(){
        return mUserId > -1;
    }

}
