<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログインページ</title>
</head>
<body>
    <h2>ログイン</h2>
    <form action="loginServlet" method="post">
        ユーザー名: <input type="text" name="username" required><br>
        パスワード: <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
    </form>

    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>

    <hr>
    <form action="createAccount.jsp" method="get">
        <button type="submit">新規登録</button>
    </form>
</body>
</html>
