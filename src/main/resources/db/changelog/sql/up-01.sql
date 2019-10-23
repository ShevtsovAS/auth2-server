CREATE TABLE oauth_approvals
(
    userId         VARCHAR(256),
    clientId       VARCHAR(256),
    scope          VARCHAR(256),
    status         VARCHAR(10),
    expiresAt      timestamp without time zone,
    lastModifiedAt timestamp without time zone
);