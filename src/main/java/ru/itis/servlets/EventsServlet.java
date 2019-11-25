package ru.itis.servlets;

import ru.itis.entities.Event;
import ru.itis.models.ConnectionCreator;
import ru.itis.models.EventsModel;
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
import java.util.List;
import java.util.Optional;

@WebServlet(name = "events", urlPatterns = "/events")
public class EventsServlet extends HttpServlet {
    private EventsRepository eventsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventsModel model = EventsModel.getInstance();
        List<String> names = model.list();
        req.setAttribute("events", names);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/events.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String eventName = req.getParameter("but-event");
        if (eventName != null && !eventName.equals("")) {
            Optional<Event> ev = eventsRepository.findOneByName(eventName);
            if (ev.isPresent()) {
                Event event = ev.get();
                HttpSession session = req.getSession();
                session.setAttribute("event", event);
                resp.sendRedirect("/event");
            }
        } else {
            doGet(req, resp);
        }
    }

    @Override
    public void init() {
        eventsRepository = new EventsRepositoryJdbcImpl(ConnectionCreator.getConnection());
    }
}
