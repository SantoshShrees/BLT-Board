<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>スレッド一覧</title>
</head>
<body>
    <h2>ようこそ、${sessionScope.user_name} さん</h2>

    <h3>スレッド一覧</h3>
    <ul>
        <c:forEach var="thread" items="${threads}">
            <li>
                <a href="ThreadServlet?thread_id=${thread.id}">
                    ${thread.title}
                </a>
            </li>
        </c:forEach>
    </ul>

    <h3>新しいスレッド作成</h3>
    <form action="CreateThreadServlet" method="post">
        タイトル: <input type="text" name="title" required><br>
        最初の投稿: <textarea name="content" required></textarea><br>
        <input type="submit" value="作成">
    </form>
</body>
</html>
