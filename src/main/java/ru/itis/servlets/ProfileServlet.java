package ru.itis.servlets;

import ru.itis.DAO.ParticipantsDAO;
import ru.itis.entities.Event;
import ru.itis.entities.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "profile", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    private EventsRepository eventsRepository;
    private ParticipantsDAO participantsDAO;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String capacity = req.getParameter("capacity");
        String place = req.getParameter("place");
        String timeStart = req.getParameter("time-start");
        String timeEnd = req.getParameter("time-end");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String host = user.getLogin();
        List<User> participants = new ArrayList<>();
        participants.add(user);
        Event event = new Event(name, description, participants, Integer.parseInt(capacity), host, place, timeStart, timeEnd, 0);
        eventsRepository.save(event);
        participantsDAO.save(event.getId(), user.getId());
        session.setAttribute("event", event);
        resp.sendRedirect("/event");
    }

    @Override
    public void init() {
        eventsRepository = new EventsRepositoryJdbcImpl(ConnectionCreator.getConnection());
        participantsDAO = new ParticipantsDAO(ConnectionCreator.getConnection());
    }
}
