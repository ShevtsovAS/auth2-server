INSERT INTO oauth_client_details(client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('my-client-name',
        '$2a$12$5LfN/eRsULpXl.KXiPjAPusk0RQ6odoEuUrWDROX6MK3u6zJRyJje', --qwerty123
        'http://localhost:8080/code',
        'READ,WRITE',
        '3600',
        '10000',
        'inventory,payment',
        'authorization_code,password,refresh_token,implicit',
        '{}');

INSERT INTO PERMISSION (NAME)
VALUES ('create_profile'),
       ('read_profile'),
       ('update_profile'),
       ('delete_profile');

INSERT INTO role (NAME)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID)
VALUES (1, 1), /*create-> admin */
       (2, 1), /* read admin */
       (3, 1), /* update admin */
       (4, 1), /* delete admin */
       (2, 2), /* read operator */
       (3, 2); /* update operator */

insert into "user" (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked)
VALUES ('1', 'admin', '$2a$12$x4oRisMXAznLsRLlAD5KDerGpNpa0sH.hylIMl7QccguMbuUo4aDC', 'admin@example.com', '1', '1', '1', '1');

insert into "user" (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked)
VALUES ('2', 'user', '{bcrypt}$2a$12$az9mzaxZGlbJGfuUHZkej.aXMHrTBg/.zer0N6JgO2KnRHCTbS8zm', 'user@example.com', '1', '1', '1', '1');

INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
VALUES (1, 1),
       (2, 2);