package com.fjern.app.web.util;

public class UtilMappings {

    public static final String ACTIVATE_USER = "activateUser";


    public static final class Plural {

        public static final String ROLES = "roles";

        public static final String USERS = "users";

        public static final String POSTS = "posts";

    }

    public static final class Hateoas {
        private static final String HATEOAS = "/hateoas/";

        public static final String ROLES = HATEOAS + Plural.ROLES;

        public static final String USER = HATEOAS + Plural.USERS;

    }

    public static final class Services {

        public static final String USER_SERVICE = "user-service";

    }
}
