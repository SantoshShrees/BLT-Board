<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="ja">

        <head>
            <meta charset="UTF-8" />
            <title>投稿一覧</title>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css" />
            <link href="https://fonts.googleapis.com/css?family=Philosopher" rel="stylesheet" />
            <link rel="stylesheet" href="css/post_list.css" />
        </head>

        <body>
            <div class="page">
                <!-- Fixed top-right logout -->
                <a href="logout" class="logout"><button type="button">ログアウト</button></a>

                <!-- Non-scrolling header -->
                <header class="page-header">
                    <h2>ようこそ、${sessionScope.user_name} さん</h2>
                    <h2>スレッド: [${threadId}] ${threadTitle}</h2>
                </header>

                <!-- Middle: only this list scrolls -->
                <main class="content">
                    <h3>投稿リスト</h3>
                    <div class="list-scroll">
                        <ul>
                            <c:forEach var="post" items="${posts}">
                                <li>
                                    <p>投稿番号: ${post.number}</p>
                                    <p>${post.user} さん（${post.date}）</p>
                                    <p>${post.content}</p>

                                    <!-- Delete -->
                                    <form action="delete" method="post">
                                        <input type="hidden" name="post_id" value="${post.id}" />
                                        <button type="submit">削除</button>
                                    </form>

                                    <!-- Per-post error -->
                                    <c:if test="${sessionScope.errorPostId == post.id}">
                                        <p style="color:red;">${sessionScope.errorMsg}</p>
                                        <c:remove var="errorPostId" scope="session" />
                                        <c:remove var="errorMsg" scope="session" />
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <!-- Non-scrolling bottom composer -->
                    <section class="composer">
                        <hr />
                        <h3>新投稿の追加</h3>
                        <form action="post?thread_id=${threadId}" method="post">
                            ポスト文: <input type="text" name="content" required />
                            <input type="submit" value="投稿" />
                        </form>
                        <a href="threads"><button type="button">スレッド一覧へ戻る</button></a>
                    </section>
                </main>
            </div>
        </body>

        </html>