package servlet;

import dao.Db;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        HttpSession session = req.getSession();
        int loginUserId = (int) session.getAttribute("user_id");

        try (Connection cn = Db.getConnection()) {

            //if the request contains post id you can delete post
            if (req.getParameter("post_id") != null) {
                int postId = Integer.parseInt(req.getParameter("post_id"));

                //get owner of post
                String sqlCheck = "SELECT user_id, thread_id FROM POSTS WHERE post_id = ?";
                int postOwnerId = -1;
                int threadId = -1;

                try (PreparedStatement ps = cn.prepareStatement(sqlCheck)) {
                    ps.setInt(1, postId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        postOwnerId = rs.getInt("user_id");
                        threadId = rs.getInt("thread_id");
                    } else {
                        throw new ServletException("Post not found.");
                    }
                }

                // Check permission if not the owner then get the error message 
                if (loginUserId != postOwnerId) {
                    session.setAttribute("errorPostId", postId);
                    session.setAttribute("errorMsg", "作成者以外は削除できません。");
                    res.sendRedirect("post?thread_id=" + threadId);
                    return;
                }
                // Delete post
                String sqlDelete = "DELETE FROM POSTS WHERE post_id = ?";
                try (PreparedStatement ps = cn.prepareStatement(sqlDelete)) {
                    ps.setInt(1, postId);
                    ps.executeUpdate();
                }

                //redirect to the same thread
                res.sendRedirect("post?thread_id=" + threadId);
                return;
            }

            //if the request contains thread id then delete thread
            else if (req.getParameter("thread_id") != null) {
                int threadId = Integer.parseInt(req.getParameter("thread_id"));

                //get who own the thread
                String sqlCheck = "SELECT user_id FROM THREADS WHERE thread_id = ?";
                int threadOwnerId = -1;

                try (PreparedStatement ps = cn.prepareStatement(sqlCheck)) {
                    ps.setInt(1, threadId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        threadOwnerId = rs.getInt("user_id");
                    } else {
                        throw new ServletException("Thread not found.");
                    }
                }

                // Check permission
                if (loginUserId != threadOwnerId) {
                    session.setAttribute("errorThreadId", threadId);
                    session.setAttribute("errorMsg", "作成者以外は削除できません。");
                    res.sendRedirect("threads");
                    return;
                }

                //delete thread
                String sqlDeleteThread = "DELETE FROM THREADS WHERE thread_id = ?";
                try (PreparedStatement ps = cn.prepareStatement(sqlDeleteThread)) {
                    ps.setInt(1, threadId);
                    ps.executeUpdate();
                }

                //Redirect back to thread list
                res.sendRedirect("threads");
                return;
            }

            //when there is not any id
            else {
                throw new ServletException("ID がみつけられないけど。");
            }

        } catch (SQLException e) {
            throw new ServletException("エラーが発生しました", e);
        }
    }
}
