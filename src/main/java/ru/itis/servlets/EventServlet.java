package ru.itis.servlets;

import ru.itis.entities.Event;
import ru.itis.models.ConnectionCreator;
import ru.itis.repositories.EventsRepository;
import ru.itis.repositories.EventsRepositoryJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "event", urlPatterns = "/event")
public class EventServlet extends HttpServlet {
    private EventsRepository eventsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/event.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String capacity = req.getParameter("capacity");
        String host = req.getParameter("host");
        Boolean active = Boolean.valueOf(req.getParameter("active"));
        String place = req.getParameter("place");
        String timeStart = req.getParameter("time-start");
        String timeEnd = req.getParameter("time-end");
        HttpSession session = req.getSession();
        Event event = new Event(Long.valueOf(6), name, description, Integer.parseInt(capacity), host, active, place, timeStart, timeEnd, 0);
        eventsRepository.update(event);
        session.setAttribute("event", event);
        doGet(req, resp);
    }

    @Override
    public void init() {
        eventsRepository = new EventsRepositoryJdbcImpl(ConnectionCreator.getConnection());
    }
}
