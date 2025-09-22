package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ThreadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "bbs11";
    private static final String PASS = "luck";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String threadId = request.getParameter("thread_id");

        List<Map<String, Object>> posts = new ArrayList<>();
        String threadTitle = "";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            // get thread title
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT thread_title FROM threads WHERE thread_id=?")) {
                ps.setString(1, threadId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        threadTitle = rs.getString("thread_title");
                    }
                }
            }

            // get posts for this thread
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT p.post_number, p.post_content, p.post_date, u.user_name " +
                    "FROM posts p JOIN users u ON p.user_id = u.user_id " +
                    "WHERE p.thread_id=? ORDER BY p.post_number")) {
                ps.setString(1, threadId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Map<String, Object> post = new HashMap<>();
                        post.put("number", rs.getInt("post_number"));
                        post.put("content", rs.getString("post_content"));
                        post.put("date", rs.getDate("post_date"));
                        post.put("user", rs.getString("user_name"));
                        posts.add(post);
                    }
                }
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.setAttribute("threadId", threadId);
        request.setAttribute("threadTitle", threadTitle);
        request.setAttribute("posts", posts);

        RequestDispatcher rd = request.getRequestDispatcher("thread.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String threadId = request.getParameter("threadId");
        String userId = request.getParameter("userId");
        String content = request.getParameter("content");

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO posts (post_id, thread_id, user_id, post_number, post_content, post_date) " +
                         "VALUES (SEQ_POSTS.NEXTVAL, ?, ?, (SELECT NVL(MAX(post_number),0)+1 FROM posts WHERE thread_id=?), ?, SYSDATE)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, threadId);
                ps.setString(2, userId);
                ps.setString(3, threadId);
                ps.setString(4, content);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        // redirect to doGet
        response.sendRedirect("ThreadServlet?thread_id=" + threadId);
    }
}
