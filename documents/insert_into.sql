--users
INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (1, '������', 'shoji');

INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (2, 'kure', 'kure');

INSERT INTO USERS (user_id, user_name, user_pass)
VALUES (3, '�_�l', 'god');

--threads
INSERT INTO THREADS (thread_id, thread_title)
VALUES (1, '���Ȓ��̑f���炵���ɂ���');

INSERT INTO THREADS (thread_id, thread_title)
VALUES (2, '�S�\�̐_�l�̓J�b�v���[�����ɂ��������Ȃ��Ă��H�ׂ���̂��H');

INSERT INTO THREADS (thread_id, thread_title)
VALUES (3, '�~�Ɨr���Ăǂ��������������H');

--POSTS
INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (1, 1, 1, 1, '�f���炵���Ɍ��܂��Ă�', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (2, 1, 2, 2, '���̑O�������Ō���', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (3, 1, 3, 3, '������Ō����Ă�', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (4, 2, 2, 1, '�����Ⴞ�߂Ȃ�H', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (5, 2, 3, 2, '���̂܂܃o���o���H�����', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (6, 2, 2, 1, '�r����', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (7, 3, 2, 2, '��������Ă��ׂ���́H', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (8, 3, 3, 3, '�߂����Ⴈ��������I', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (9, 3, 1, 4, '�r���ăI�X�H���X�H', sysdate);

INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date)
VALUES (10, 3, 1, 5, '�q�r����������ˁI', sysdate);

--COMMENTS
INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 1, 1, 'hahahha', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 3, 2, '������', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 4, 3, '�f���炵���ł���', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 2, 3, '�G�B�B�B�т�����', sysdate);

INSERT INTO COMMENTS (comment_id, post_id, user_id, content, comment_date)
VALUES (1, 10, 1, '�����������B�B�B�Ȃ�ق�', sysdate);

