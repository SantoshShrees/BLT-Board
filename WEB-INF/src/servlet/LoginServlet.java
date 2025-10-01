package servlet;

import dao.Db;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection cn = Db.getConnection()){
            String sql = "SELECT * FROM USERS WHERE user_name=? AND user_pass=?";
            PreparedStatement stmt = cn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                //save both user id and the user name into the session
                HttpSession session = req.getSession();
                session.setAttribute("user_id", userId);
                session.setAttribute("user_name", userName);

                res.sendRedirect("threads");

            }
            else {
                // Login fail → forward to error page
                RequestDispatcher dis = req.getRequestDispatcher("error.jsp");
                dis.forward(req, res);

            }
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
}
