package servlet;

import dao.Db;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection cn = Db.getConnection()) {
            String sql = "INSERT INTO USERS (user_id, user_name, user_pass) VALUES (SEQ_USERS.NEXTVAL, ?, ?)";
            PreparedStatement stmt = cn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

            // 成功したらログインページへ
            res.sendRedirect("login.jsp");

        } catch (SQLException e) {
            throw new ServletException("ユーザー登録中にエラーが発生しました。", e);
        }
    }
}
