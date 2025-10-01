package servlet;

import dao.Db;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class ThreadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        List<Map<String, Object>> threadList = new ArrayList<>();

        try (Connection cn = Db.getConnection()) {
            String sql = "SELECT thread_id, thread_title FROM THREADS ORDER BY thread_id DESC";
            PreparedStatement stmt = cn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> thread = new HashMap<>();
                thread.put("id", rs.getInt("thread_id"));
                thread.put("title", rs.getString("thread_title"));
                threadList.add(thread);
            }
        } catch (SQLException e) {
            throw new ServletException("DB error while fetching threads", e);
        }

        req.setAttribute("threads", threadList);
        req.getRequestDispatcher("thread_list.jsp").forward(req, res);
    }
    
}
