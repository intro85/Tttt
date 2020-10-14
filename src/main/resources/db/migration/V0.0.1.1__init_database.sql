CREATE TABLE URLS (
  id                         bigint NOT NULL,
  url                        VARCHAR(255) NOT NULL,
  status                     VARCHAR(10) NOT NULL DEFAULT 'NEW',
  http_code                  bigint DEFAULT NULL,
  PRIMARY KEY (id)
);