CREATE TABLE oauth_client_details
(
    client_id               VARCHAR(255) PRIMARY KEY,
    client_secret           VARCHAR(255) NOT NULL,
    web_server_redirect_uri VARCHAR(2048),
    scope                   VARCHAR(255),
    access_token_validity   integer,
    refresh_token_validity  integer,
    resource_ids            VARCHAR(1024),
    authorized_grant_types  VARCHAR(1024),
    authorities             VARCHAR(1024),
    additional_information  VARCHAR(4096),
    autoapprove            VARCHAR(255)
);

COMMENT ON COLUMN oauth_client_details.client_id IS 'Идентификатор пользователя (имя, email, телефон)';
COMMENT ON COLUMN oauth_client_details.client_secret IS 'Пароль пользователя';
COMMENT ON COLUMN oauth_client_details.web_server_redirect_uri IS 'url пользователя где он хочет авторизоваться';


CREATE TABLE permission
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(512) UNIQUE
);

COMMENT ON COLUMN permission.id IS 'Permission id';
COMMENT ON COLUMN permission.name IS 'Permission name';

CREATE TABLE role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE "user"
(
    id                    SERIAL PRIMARY KEY,
    username              VARCHAR(100)  NOT NULL UNIQUE,
    password              VARCHAR(1024) NOT NULL,
    email                 VARCHAR(1024) NOT NULL,
    enabled               smallint      NOT NULL,
    accountNonExpired     smallint      NOT NULL,
    credentialsNonExpired smallint      NOT NULL,
    accountNonLocked      smallint      NOT NULL
);

CREATE TABLE permission_role
(
    permission_id integer NOT NULL REFERENCES permission (id),
    role_id       integer NOT NULL REFERENCES role (id),
    PRIMARY KEY (permission_id, role_id)
);

CREATE TABLE role_user
(
    role_id integer NOT NULL REFERENCES role (id),
    user_id integer NOT NULL REFERENCES "user" (id),
    PRIMARY KEY (role_id, user_id)
);

CREATE TABLE oauth_client_token
(
    token_id          VARCHAR(256),
    token             bytea,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

CREATE TABLE oauth_access_token
(
    token_id          VARCHAR(256),
    token             bytea,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    bytea,
    refresh_token     VARCHAR(256)
);

CREATE TABLE oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          bytea,
    authentication bytea
);

CREATE TABLE oauth_code
(
    code           VARCHAR(256),
    authentication bytea
);