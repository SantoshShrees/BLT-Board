-- USERS
INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (1, 'いさか', 'shoji');

INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (2, 'kure', 'kure');

INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (3, '神様', 'god');

-- THREADS
INSERT INTO THREADS (thread_id, thread_title, user_id)
VALUES (1, '井坂科長の素晴らしさについて', 1);

INSERT INTO THREADS (thread_id, thread_title, user_id)
VALUES (2, '全能の神様はカップラーメンにお湯を入れなくても食べられるのか？', 2);

INSERT INTO THREADS (thread_id, thread_title, user_id)
VALUES (3, '鯨と羊ってどっちがおいしい？', 3);

-- POSTS
INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (1, 1, 1, 1, '素晴らしいに決まってる', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (2, 1, 2, 2, 'この前中央線で見た', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (3, 1, 3, 3, '東中野で光ってた', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (4, 2, 2, 1, '水じゃだめなん？', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (5, 2, 3, 2, 'そのままバリバリ食えるべ', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (6, 2, 2, 3, '羊だね', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (7, 3, 2, 1, 'くじらってたべられるの？', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (8, 3, 3, 2, 'めっちゃおいしいよ！', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (9, 3, 1, 3, '羊ってオス？メス？', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (10, 3, 1, 4, '子羊美味しいよね！', sysdate);

COMMIT;
