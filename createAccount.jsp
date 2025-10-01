<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新規登録</title>
</head>
<body>
    <h2>新規登録</h2>
    <p style="color:red">${error}</p>
    <p style="color:green">${success}</p>

    <form action="CreateAccountServlet" method="post">
        ユーザー名: <input type="text" name="username"><br>
        パスワード: <input type="password" name="password"><br>
        <input type="submit" value="登録">
    </form>
    <a href="login.jsp"><button>戻る</button></a>
</body>
</html>
