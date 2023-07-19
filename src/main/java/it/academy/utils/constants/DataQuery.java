package it.academy.utils.constants;

public class DataQuery {
    public static final String FIND_USER_BY_USERNAME = "findUserByUsername";
    public static final String QUERY_FIND_BY_USERNAME = "select * from users where username=:username";
    public static final String PARAM_USERNAME = "username";
    public static final String FIND_ROLE_BY_NAME = "findRoleByName";
    public static final String QUERY_FIND_BY_NAME = "select * from role where name=:name";
    public static final String PARAM_NAME = "name";
    public static final String PERCENT_STRING = "%";
    public static final String STRING_FROM = "FROM ";
}
