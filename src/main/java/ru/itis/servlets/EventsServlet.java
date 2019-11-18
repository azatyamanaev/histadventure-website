package ru.itis.servlets;

import ru.itis.models.EventsModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "events", urlPatterns = "/events")
public class EventsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean auth = (Boolean) session.getAttribute("auth");
        if (auth != null && auth) {
            EventsModel model = EventsModel.getInstance();
            List<String> names = model.list();
            req.setAttribute("events", names);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/events.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
