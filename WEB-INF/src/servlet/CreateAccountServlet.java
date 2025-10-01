package servlet;

import dao.Db;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.setCharacterEncoding("UTF-8");

        //check if the user name and the password is filled
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "ユーザー名とパスワードを入力してください。");
            RequestDispatcher rd = req.getRequestDispatcher("createAccount.jsp");
            rd.forward(req, res);
            return;
        }
        try (Connection cn = Db.getConnection()) {

            //Check if username and password already exists
            String check = "SELECT COUNT(*) FROM USERS WHERE user_name = ? and user_pass = ?";
            try (PreparedStatement checkStmt = cn.prepareStatement(check)) {
                checkStmt.setString(1, username);
                checkStmt.setString(2,password);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    req.setAttribute("error", "このユーザー名はすでに登録されています！");
                    RequestDispatcher rd = req.getRequestDispatcher("createAccount.jsp");
                    rd.forward(req, res);
                    return;
                }
            }

            //Insert new account
            String sql = "INSERT INTO USERS (user_id, user_name, user_pass) VALUES (SEQ_USERS.NEXTVAL, ?, ?)";
            try (PreparedStatement insertStmt = cn.prepareStatement(sql)) {
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();
            }

            //Succesfully signedup and get the message
            req.setAttribute("success", "新規登録が完了しました。ログインペーににしてください。");
            RequestDispatcher rd = req.getRequestDispatcher("createAccount.jsp");
            rd.forward(req, res);
        } catch (SQLException e) {
            throw new ServletException("ユーザー登録中にエラーが発生しました。", e);
        }
    }
}
