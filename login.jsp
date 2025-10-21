<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログインページ</title>
    <link rel="stylesheet" href="css/login_style.css">
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<body>
    <div class="container">
        <!-- Left side welcome panel -->
        <div class="welcome-box">
            <h1>BBS11へようこそ！</h1>
            <p>掲示板システムにログインして交流を始めましょう。</p>
        </div>

        <!-- Right side login form -->
        <div class="form-box">
            <h2>ログイン</h2>

            <form action="loginServlet" method="post">
                <div class="input-box">
                    <input type="text" name="username" required placeholder="ユーザー名">
                </div>

                <div class="input-box">
                    <input type="password" name="password" required placeholder="パスワード">
                </div>

                <button type="submit" class="btn">ログイン</button>

                <c:if test="${not empty error}">
                    <p class="error">${error}</p>
                </c:if>
            </form>

            <form action="createAccount.jsp" method="get">
                <button type="submit" class="btn">新規登録</button>
            </form>
        </div>
    </div>
</body>
</html>
