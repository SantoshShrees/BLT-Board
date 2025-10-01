package servlet;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class CreateThreadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "bbs11";
    private static final String PASS = "luck";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // ✅ Always take userId from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            throw new ServletException("ログインしていません。");
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                // Insert thread
                String sql1 = "INSERT INTO THREADS (thread_id, thread_title) VALUES (SEQ_THREADS.NEXTVAL, ?)";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1, title);
                ps1.executeUpdate();

                // Insert first post
                String sql2 = "INSERT INTO POSTS (post_id, thread_id, user_id, post_number, post_content, post_date) " +
                        "VALUES (SEQ_POSTS.NEXTVAL, SEQ_THREADS.CURRVAL, ?, 1, ?, SYSDATE)";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setInt(1, userId);
                ps2.setString(2, content);
                ps2.executeUpdate();
            }

            // ✅ Better: redirect to thread list servlet
            response.sendRedirect("ThreadListServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
