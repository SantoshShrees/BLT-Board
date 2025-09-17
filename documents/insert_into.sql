--users
INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (1, 'いさか', 'shoji');

INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (2, 'kure', 'kure');

INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (3, '神様', 'god');

--threads
INSERT INTO THREADS (thread_id, thread_title)
VALUES (1, '井坂科長の素晴らしさについて');

INSERT INTO THREADS (thread_id, thread_title)
VALUES (2, '全能の神様はカップラーメンにお湯を入れなくても食べられるのか？');

INSERT INTO THREADS (thread_id, thread_title)
VALUES (3, '鯨と羊ってどっちがおいしい？');

--POSTS
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
VALUES (6, 2, 2, 1, '羊だね', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (7, 3, 2, 2, 'くじらってたべられるの？', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (8, 3, 3, 3, 'めっちゃおいしいよ！', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (9, 3, 1, 4, '羊ってオス？メス？', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (10, 3, 1, 5, '子羊美味しいよね！', sysdate);

--COMMENTS
INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 1, 1, 'hahahha', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 3, 2, 'いいね', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 4, 3, '素晴らしいですね', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 2, 3, 'エ。。。びっくり', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 10, 1, 'あああああ。。。なるほど', sysdate);

