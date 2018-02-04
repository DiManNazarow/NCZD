package ru.mbg.nczd.utils;

import android.content.Context;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 17.01.2018.
 */

public class Params {

    public static final String LOGIN_ACTION = "auth_action";

    public static final String LOGIN_SUCCESS = "sign_in_success";

    public static final String REGISTER_ACTION = "register_action";

    public static final String REGISTER_SUCCESS = "register_success";

    public static final String PERSONAL_INFO_SUCCESS_UPDATE = "personal_info_success_update";

    public static final String RECEPTION_ACTION = "reception_action";

    public static final String RECEPTION_ADD_ACTION = "reception_add_action";

    public static final String ADD_CHILD_ACTION = "add_child_action";

    public static final int LOGIN_REQUEST_CODE = 1000;

    public static final int REGISTER_REQUEST_CODE = 1001;

    public static final int ADD_CHILD_REQUEST_CODE = 1002;

    public static final int EDIT_CHILD_REQUEST_CODE = 1003;

    public static final String USER_ID_ARG = "user_id_args";

    public static final String RECEPTION_ID_ARG = "reception_id_arg";

    public static final String UPDATE_PROFILE_ACTION = "update_profile_action";

    public static final String RECEPTION_TYPE_ARG = "reception_type_arg";

    public static final String CHILD_ID_ARG = "child_id_arg";

    public enum RECEPTION_TYPE {
        COSMETOLOGIST_RECEPTION {
            @Override
            public int getReceptionTypeId() {
                return 0;
            }

            @Override
            public String getReceptionName(Context context) {
                return context.getString(R.string.reception_cosmetologist_reception);
            }

            @Override
            public String getName(Context context) {
                return context.getString(R.string.reception_cosmetologist);
            }
        },
        NEUROLOGIST_RECEPTION {
            @Override
            public int getReceptionTypeId() {
                return 1;
            }

            @Override
            public String getReceptionName(Context context) {
                return context.getString(R.string.reception_neurologist_reception);
            }

            @Override
            public String getName(Context context) {
                return context.getString(R.string.reception_neurologist);
            }
        },
        PSYCHOLOGIST_RECEPTION {
            @Override
            public int getReceptionTypeId() {
                return 2;
            }

            @Override
            public String getReceptionName(Context context) {
                return context.getString(R.string.reception_psychologist_reception);
            }

            @Override
            public String getName(Context context) {
                return context.getString(R.string.reception_psychologist);
            }
        },
        MAMMOLOGIST_RECEPTION {
            @Override
            public int getReceptionTypeId() {
                return 3;
            }

            @Override
            public String getReceptionName(Context context) {
                return context.getString(R.string.reception_mammologist_reception);
            }

            @Override
            public String getName(Context context) {
                return context.getString(R.string.reception_mammologist);
            }
        },
        CT_SCAN_RECEPTION {
            @Override
            public int getReceptionTypeId() {
                return 4;
            }

            @Override
            public String getReceptionName(Context context) {
                return context.getString(R.string.reception_ct_scan_reception);
            }

            @Override
            public String getName(Context context) {
                return context.getString(R.string.reception_ct_scan);
            }
        },
        MRI_RECEPTION {
            @Override
            public int getReceptionTypeId() {
                return 5;
            }

            @Override
            public String getReceptionName(Context context) {
                return context.getString(R.string.reception_mri_reception);
            }

            @Override
            public String getName(Context context) {
                return context.getString(R.string.reception_mri);
            }
        },
        X_RAY_RECEPTION {
            @Override
            public int getReceptionTypeId() {
                return 6;
            }

            @Override
            public String getReceptionName(Context context) {
                return context.getString(R.string.reception_x_ray_reception);
            }

            @Override
            public String getName(Context context) {
                return context.getString(R.string.reception_x_ray);
            }
        };

        public abstract int getReceptionTypeId();

        public abstract String getReceptionName(Context context);

        public abstract String getName(Context context);

        public static RECEPTION_TYPE getById(int id){
            return values()[id];
        }

    }

}
