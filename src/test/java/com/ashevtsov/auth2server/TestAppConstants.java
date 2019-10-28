package com.ashevtsov.auth2server;

public interface TestAppConstants {
    String TEST_USER_NAME = "user";
    String TEST_USER_PASSWORD = "user";
    String USER_NAME_PARAM = "username";
    String USER_PASSWORD_PARAM = "password";
    String GRANT_TYPE_PARAM = "grant_type";
    String TOKEN_PARAM = "token";
    String TEST_USER_GRANT_TYPE = "password";
    String OAUTH_TOKEN_URL = "/oauth/token";
    String CHECK_TOKEN_URL = "/oauth/check_token";
    String HTTP_BASIC_USER_NAME = "my-client-name";
    String HTTP_BASIC_USER_PASSWORD = "qwerty123";
    String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    String ACCESS_TOKEN = "access_token";
}
