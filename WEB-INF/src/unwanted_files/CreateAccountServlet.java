package servlet;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "bbs11";
    private static final String PASS = "luck";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                String sql = "INSERT INTO USERS (user_id, user_name, user_pass) VALUES (SEQ_USERS.NEXTVAL, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
            }

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
