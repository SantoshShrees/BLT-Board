<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新規登録</title>
    <link rel="stylesheet" href="css/createAccount.css">
    <link href="https://fonts.googleapis.com/css?family=Philosopher" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Left side -->
        <div class="form-box">
            <h2>新規登録</h2>

            <p class="error">${error}</p>
            <p class="success">${success}</p>

            <form action="CreateAccountServlet" method="post">
                <div class="input-box">
                    <input type="text" name="username" required placeholder="ユーザー名">
                </div>

                <div class="input-box">
                    <input type="password" name="password" required placeholder="パスワード">
                </div>

                <button type="submit" class="btn">登録</button>
            </form>

            <a href="login.jsp" class="btn ghost">戻る</a>
        </div>

        <!-- Right side -->
        <div class="welcome-box">
            <h1>BBS11 新規登録</h1>
            <p>アカウントを作成して掲示板を利用しましょう。</p>
        </div>
    </div>
</body>
</html>
