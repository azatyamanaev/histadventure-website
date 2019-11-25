package ru.itis.servlets;

import ru.itis.entities.User;
import ru.itis.models.ConnectionCreator;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "other-profile", urlPatterns = "/other-profile")
public class OtherProfileServlet extends HttpServlet {
    private UsersRepository usersRepository;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String userLogin = req.getParameter("but-user");
        if (userLogin != null){
            Optional<User> u = usersRepository.findOneByLogin(userLogin);
            if (u.isPresent()) {
                User user = u.get();
                session.setAttribute("otherUser", user);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/other-profile.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
    @Override
    public void init() {
        usersRepository = new UsersRepositoryJdbcImpl(ConnectionCreator.getConnection());
    }
}
