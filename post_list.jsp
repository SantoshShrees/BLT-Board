<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>投稿一覧</title>
</head>
<body>
    <h2>ようこそ、${sessionScope.user_name} さん</h2>

    <!-- Logout -->
    <a href="logout">
        <button type="button">ログアウト</button>
    </a>

    <!-- Thread info -->
    <h2>スレッド: [${threadId}] ${threadTitle}</h2>

    <h3>投稿リスト</h3>
    <ul>
        <c:forEach var="post" items="${posts}">
            <li>
                投稿番号: ${post.number}<br>
                ${post.user} さん (${post.date})<br>
                ${post.content}
                <form action="delete" method="post">
                    <input type="hidden" name="post_id" value="${post.id}">
                    <button type="submit">削除</button>
                </form>
                <c:if test="${sessionScope.errorPostId == post.id}">
                    <p style="color:red;">${sessionScope.errorMsg}</p>
                    <!-- remove after displaying once -->
                    <c:remove var="errorPostId" scope="session"/>
                    <c:remove var="errorMsg" scope="session"/>
                </c:if>
            </li>
        </c:forEach>
    </ul>

    <hr>
    <h3>新投稿の追加</h3>
        <form action="post?thread_id=${threadId}" method="post">
            ポスト文:<input type="text" name="content" required>
            <input type="submit" value="投稿">
        </form>
    <a href="threads"><button type="button">スレッド一覧へ戻る</button></a>
</body>
</html>
