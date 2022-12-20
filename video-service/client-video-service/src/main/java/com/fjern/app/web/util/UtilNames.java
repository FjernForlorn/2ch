package com.fjern.app.web.util;

public class UtilNames {
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASS = "adminpass";
    public static final String ADMIN_EMAIL = "admin@mel.com";

    public static final String USER_USERNAME = "user";
    public static final String USER_PASS = "userpass";
    public static final String USER_EMAIL = "user@mel.com";


    public static final class Privileges {
        public static final String CAN_USER_READ = "ROLE_USER_READ";
        public static final String CAN_USER_WRITE = "ROLE_USER_WRITE";

        public static final String CAN_ROLE_READ = "ROLE_ROLE_READ";
        public static final String CAN_ROLE_WRITE = "ROLE_ROLE_WRITE";

        public static final String CAN_PRIVILEGES_READ = "ROLE_PRIVILEGES_READ";
        public static final String CAN_PRIVILEGES_WRITE = "ROLE_PRIVILEGES_WRITE";
    }

    public static final class Roles {
        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    }

}
