CREATE DATABASE IF NOT EXISTS socialmedia;

USE socialmedia;

CREATE TABLE IF NOT EXISTS user(
    user_id     INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_name   VARCHAR(256)  NOT NULL,
    email       VARCHAR(256)  NOT NULL,
    password    VARCHAR(256)  NOT NULL,
    cover_image VARCHAR(256)  ,
    biography   VARCHAR(256)  NOT NULL
);

CREATE TABLE IF NOT EXISTS post(
    post_id     INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id     INT           NOT NULL,
    content     VARCHAR(256)  NOT NULL,
    image       VARCHAR(256),
    create_at   DATETIME      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS comment(
    comment_id  INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id     INT           NOT NULL,
    post_id     INT           NOT NULL,
    content     VARCHAR(256)  NOT NULL,
    create_at   DATETIME      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (post_id) REFERENCES post(post_id)
);