package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ThreadListServlet extends HttpServlet {
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "bbs11";
    private static final String PASS = "luck";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Map<String,Object>> threads = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT thread_id, thread_title FROM threads ORDER BY thread_id";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String,Object> row = new HashMap<>();
                    row.put("id", rs.getInt("thread_id"));
                    row.put("title", rs.getString("thread_title"));
                    threads.add(row);
                }
            }
        } catch(Exception e) { throw new ServletException(e); }

        req.setAttribute("threads", threads);
        req.getRequestDispatcher("threadslist.jsp").forward(req, resp);
    }
}
