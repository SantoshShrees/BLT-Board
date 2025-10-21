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

        // check if session has error message
        Object err = req.getSession().getAttribute("err");
        if (err != null) {
            req.setAttribute("err", err);
            req.getSession().removeAttribute("err");
        }

        List<Map<String, Object>> threadList = new ArrayList<>();

        try (Connection cn = Db.getConnection()) {
            String sql = "SELECT thread_id, thread_title, user_id FROM THREADS ORDER BY thread_id DESC";
            PreparedStatement stmt = cn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> thread = new HashMap<>();
                thread.put("id", rs.getInt("thread_id"));
                thread.put("title", rs.getString("thread_title"));
                threadList.add(thread);
            }
        } catch (SQLException e) {
            throw new ServletException("エラーが発生しました", e);
        }

        req.setAttribute("threads", threadList);
        req.getRequestDispatcher("thread_list.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");

        if (title == null || title.trim().isEmpty()) {
            req.getSession().setAttribute("err", "入力して下さい");
            res.sendRedirect("threads");
            return;
        }

        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            throw new ServletException("ログインされていません");
        }

        try (Connection cn = Db.getConnection()) {

            // === changed: compute next thread_id as MAX+1 ===
            int newId = 1;
            String sqlMax = "SELECT NVL(MAX(thread_id), 0) + 1 FROM THREADS";
            try (PreparedStatement psMax = cn.prepareStatement(sqlMax);
                ResultSet rs = psMax.executeQuery()) {
                if (rs.next()) {
                    newId = rs.getInt(1);
                }
            }

            // === changed: insert using the computed thread_id (no NEXTVAL) ===
            String sql = "INSERT INTO THREADS (thread_id, thread_title, user_id) VALUES (?, ?, ?)";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, newId);
                ps.setString(2, title);
                ps.setInt(3, userId);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new ServletException("エラーが発生しました", e);
        }
        res.sendRedirect("threads");
    }
}
