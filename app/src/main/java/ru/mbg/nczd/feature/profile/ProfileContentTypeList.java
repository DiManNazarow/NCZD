package ru.mbg.nczd.feature.profile;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public enum  ProfileContentTypeList {

    SIGN_IN_TYPE {
        @Override
        public int getLayoutId() {
            return R.layout.layout_sign_in;
        }
    };

    public abstract int getLayoutId();

}
