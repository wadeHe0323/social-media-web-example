-- User
INSERT INTO user(user_name, password, email, cover_image, biography) VALUES (:username, :password, :email, :coverImage, :biography);
SELECT user_id, user_name, email, password, cover_image, biography FROM user WHERE user_name = :username;
SELECT user_id, user_name, email, password, cover_image, biography FROM user WHERE user_id = :userId;
-- Post
INSERT INTO post(user_id, content, create_at) VALUES (:userId, :content, :createAt);
SELECT post_id, user_id, content, create_at FROM post ORDER BY create_at DESC;
SELECT post_id, user_id, content, create_at FROM post WHERE user_id = :userId ORDER BY create_at DESC;
SELECT post_id, user_id, content, create_at FROM post WHERE post_id = :postId;
UPDATE post SET content = :content, create_at = :createAt WHERE post_id = :postId;
DELETE FROM post WHERE post_id = :postId;
-- Comment
INSERT INTO comment(user_id, post_id, content, create_at) VALUES (:userId, :postId, :content, :createAt);
SELECT comment_id, user_id, post_id, content, create_at FROM comment WHERE post_id = :postId;
DELETE FROM comment WHERE post_id = :postId;