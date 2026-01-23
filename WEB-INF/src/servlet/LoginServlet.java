package servlet;

import dao.Db;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection cn = Db.getConnection()) {

            String sql = "SELECT * FROM USERS WHERE user_name = ? AND user_pass = ?";
            try (PreparedStatement stmt = cn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int userId = rs.getInt("user_id");
                        String userName = rs.getString("user_name");

                        // save both user id and user name into the session
                        HttpSession session = req.getSession();
                        session.setAttribute("user_id", userId);
                        session.setAttribute("user_name", userName);

                        // Login success → go to thread list
                        res.sendRedirect("threads");
                    } else {
                        // Login failed → show error page
                        RequestDispatcher dis = req.getRequestDispatcher("error.jsp");
                        dis.forward(req, res);
                    }
                }
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
