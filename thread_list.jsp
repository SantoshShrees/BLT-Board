<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="ja">

        <head>
            <meta charset="UTF-8" />
            <title>スレッド一覧</title>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <!-- Reset → Font → Page CSS -->
            <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css" />
            <link href="https://fonts.googleapis.com/css?family=Philosopher" rel="stylesheet" />
            <link rel="stylesheet" href="css/thread_list.css" />
        </head>

        <body>
            <div class="page">

                <!-- Fixed top-right logout (kept as your original link target) -->
                <a href="login.jsp" class="logout"><button type="button">ログアウト</button></a>

                <!-- Non-scrolling header -->
                <header class="page-header">
                    <h2>ようこそ、${sessionScope.user_name} さん</h2>
                    <h3>スレッド一覧</h3>
                </header>

                <!-- Main area: only the list scrolls -->
                <main class="content">
                    <div class="list-scroll">
                        <ul>
                            <c:forEach var="thread" items="${threads}">
                                <li>
                                    <p class="num">${thread.id}.</p>

                                    <!-- Title link -->
                                    <a class="title" href="post?thread_id=${thread.id}">
                                        ${thread.title}
                                    </a>

                                    <!-- Delete (light red) -->
                                    <form action="delete" method="post">
                                        <input type="hidden" name="thread_id" value="${thread.id}" />
                                        <button type="submit">削除</button>
                                    </form>

                                    <!-- Per-thread error (one-time) -->
                                    <c:if test="${sessionScope.errorThreadId == thread.id}">
                                        <div class="row-error">${sessionScope.errorMsg}</div>
                                        <c:remove var="errorThreadId" scope="session" />
                                        <c:remove var="errorMsg" scope="session" />
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <!-- Composer: stays fixed; page doesn’t scroll -->
                    <section class="composer">
                        <hr />
                        <h3>スレッドの追加</h3>
                        <p class="global-error">${err}</p>
                        <form action="threads" method="post">
                            スレッド名：<input type="text" name="title" required />
                            <input type="submit" value="追加" />
                        </form>
                    </section>
                </main>

            </div>
        </body>

        </html>