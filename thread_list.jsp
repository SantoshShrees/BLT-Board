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
    <a href="login.jsp">
    <button type="button">ログアウト</button>
</a>


    <h3>スレッド一覧</h3>
    <ul>
        <c:forEach var="thread" items="${threads}">
            <li>
                [${thread.id}]
                <a href="post?thread_id=${thread.id}">
                    ${thread.title}
                    <form action="delete" method="post">
                        <input type="hidden" name="thread_id" value="${thread.id}">
                        <button type="submit">削除</button>
                    </form>
                </a>
                <c:if test="${sessionScope.errorThreadId == thread.id}">
                    <div style="color:red">${sessionScope.errorMsg}</div>
                    <c:remove var="errorThreadId" scope="session"/>
                    <c:remove var="errorMsg" scope="session"/>
                </c:if>
            </li>
        </c:forEach>
    </ul>
    <hr>
    <h2>スレッドの追加</h2>
    <p style="color:red">${err}</p>
    <form action="threads" method="post">
    スレッド名：<input type="text" name="title">
    <input type="submit" value="追加">
    </form>

</body>
</html>
