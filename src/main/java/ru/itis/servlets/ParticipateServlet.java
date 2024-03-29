package ru.itis.servlets;

import ru.itis.entities.Event;
import ru.itis.entities.User;
import ru.itis.models.ConnectionCreator;
import ru.itis.DAO.ParticipantsDAO;
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
import java.util.Optional;

@WebServlet(name = "addParticipant", urlPatterns = "/participate")
public class ParticipateServlet extends HttpServlet {
    private ParticipantsDAO participantsRepositoryJdbc;
    private EventsRepository eventsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Event event = (Event) session.getAttribute("event");
        participantsRepositoryJdbc.save(event.getId(), user.getId());
        Optional<Event> ev = eventsRepository.find(event.getId());
        if (ev.isPresent()) {
            event = ev.get();
            session.setAttribute("event", event);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/event.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void init() {
        participantsRepositoryJdbc = new ParticipantsDAO(ConnectionCreator.getConnection());
        eventsRepository = new EventsRepositoryJdbcImpl(ConnectionCreator.getConnection());
    }
}
