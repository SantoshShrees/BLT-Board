package servlet;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "bbs11";
    private static final String PASS = "luck";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String postId = request.getParameter("postId");
        String userId = request.getParameter("userId");
        String comment = request.getParameter("comment");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                String sql = "INSERT INTO COMMENTS (comment_id, post_id, user_id, content, created_at) " +
                                "VALUES (SEQ_COMMENTS.NEXTVAL, ?, ?, ?, SYSDATE)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, postId);
                ps.setString(2, userId);
                ps.setString(3, comment);
                ps.executeUpdate();
            }

            response.sendRedirect("thread.jsp?postId=" + postId);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
