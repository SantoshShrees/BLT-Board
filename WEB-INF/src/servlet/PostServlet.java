package servlet;

import dao.Db;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;

public class PostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {

        String threadId = req.getParameter("thread_id");
        String threadTitle = "";
        List<Map<String, Object>> postList = new ArrayList<>();

        try (Connection cn = Db.getConnection()) {
            // get thread title
            String sqlThread = "SELECT thread_title FROM THREADS WHERE thread_id = ?";
            PreparedStatement stmtThread = cn.prepareStatement(sqlThread);
            stmtThread.setInt(1, Integer.parseInt(threadId));
            ResultSet rsThread = stmtThread.executeQuery();
            if (rsThread.next()) {
                threadTitle = rsThread.getString("thread_title");
            }

            // get posts inside this thread (with user_name)
            String sql = "SELECT p.post_id,p.user_id, u.user_name, p.post_number, p.post_content, p.post_date "
                        + "FROM POSTS p JOIN USERS u ON p.user_id = u.user_id "
                        +"WHERE p.thread_id = ? ORDER BY p.post_id DESC";
            PreparedStatement stmt = cn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(threadId));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> post = new HashMap<>();
                post.put("id", rs.getInt("post_id"));
                post.put("userId", rs.getInt("user_id"));
                post.put("user", rs.getString("user_name"));
                post.put("number", rs.getInt("post_number"));
                post.put("content", rs.getString("post_content"));
                post.put("date", rs.getDate("post_date"));
                postList.add(post);
            }
        } catch (SQLException e) {
            throw new ServletException("DB error while fetching posts", e);
        }

        req.setAttribute("threadId", threadId);
        req.setAttribute("threadTitle", threadTitle);
        req.setAttribute("posts", postList);
        RequestDispatcher rd = req.getRequestDispatcher("post_list.jsp");
        rd.forward(req, res);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException{
        //get tht content and the thread id to post something in it
        String postContent = req.getParameter("content");
        int threadId = Integer.parseInt(req.getParameter("thread_id"));
        //get the current user id from http session
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("user_id");

        try(Connection cn = Db.getConnection()){
            //to get post number
            int postNumber = 1;
            String sqlNum = "SELECT NVL(MAX(post_number), 0) + 1 FROM POSTS WHERE thread_id = ?";
            try (PreparedStatement psNum = cn.prepareStatement(sqlNum)) {
                psNum.setInt(1, threadId);
                ResultSet rs = psNum.executeQuery();
                if (rs.next()) postNumber = rs.getInt(1);
            }
            //insert new post
            String sql = """
                INSERT INTO POSTS
                (post_id, thread_id, user_id, post_number, post_content, post_date)
                VALUES (SEQ_POSTS.NEXTVAL, ?, ?, ?, ?, SYSDATE)
            """;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, threadId);
                ps.setInt(2, userId);
                ps.setInt(3, postNumber);
                ps.setString(4, postContent);
                ps.executeUpdate();
            }
        }catch(Exception e){
        throw new ServletException(e);
        }
        res.sendRedirect("post?thread_id=" + threadId);
    }
}
