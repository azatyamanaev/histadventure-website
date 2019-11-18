package ru.itis.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "profile", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean auth = (Boolean) session.getAttribute("auth");
        if (auth != null && auth) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/profile.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
