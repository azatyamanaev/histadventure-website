package ru.itis.servlets;

import ru.itis.DAO.SubscribedEventsDAO;
import ru.itis.entities.Event;
import ru.itis.entities.User;
import ru.itis.models.ConnectionCreator;
import ru.itis.repositories.EventsRepository;
import ru.itis.repositories.EventsRepositoryJdbcImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "subscribe", urlPatterns = "/subscribe")
public class SubscribeServlet extends HttpServlet {
    private SubscribedEventsDAO subscribedEventsDAO;
    private EventsRepository eventsRepository;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("but-ev");
        Optional<Event> ev = eventsRepository.findOneByName(name);
        if (ev.isPresent()) {
            Event event = ev.get();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            subscribedEventsDAO.save(user.getId(), event.getId());
            resp.sendRedirect("/events");
        }
    }
    @Override
    public void init() {
        subscribedEventsDAO = new SubscribedEventsDAO(ConnectionCreator.getConnection());
        eventsRepository = new EventsRepositoryJdbcImpl(ConnectionCreator.getConnection());
    }
}
