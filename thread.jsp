<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>スレッド詳細</title>
</head>
<body>
    <h2>スレッド: ${threadTitle}</h2>

    <h3>ポスト一覧</h3>
    <c:forEach var="post" items="${posts}">
        <div>
            <p>
                <strong>No.${post.number}</strong><br>
                投稿者: ${post.user}<br>
                日付: ${post.date}
            </p>
            <p>${post.content}</p>
        </div>
    </c:forEach>

    <hr>
    <h3>新しいポスト作成</h3>
    <form action="ThreadServlet" method="post">
        <input type="hidden" name="threadId" value="${threadId}">

        <input type="hidden" name="userId" value="${sessionScope.user_id}">
        内容:<br>
        <textarea name="content" rows="4" cols="60" required></textarea><br>
        <input type="submit" value="投稿">
    </form>

    <p><a href="ThreadListServlet">← スレッド一覧へ戻る</a></p>
</body>
</html>
