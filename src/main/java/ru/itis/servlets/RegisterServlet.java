package ru.itis.servlets;

import ru.itis.entities.Role;
import ru.itis.entities.User;
import ru.itis.models.ConnectionCreator;
import ru.itis.models.Validator;
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

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (Validator.validate(firstname, Validator.nameRegex) && Validator.validate(lastname, Validator.nameRegex) && Validator.validate(email, Validator.emailRegex) && Validator.validate(login, Validator.loginRegex) && Validator.validate(password, Validator.passwordRegex)) {
            int hash = password.hashCode();
            User user = new User(firstname, lastname, email, login, Integer.toString(hash), Role.VERIFIED);
            usersRepository.save(user);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("auth", true);
            resp.sendRedirect("/profile");
        } else {
            doGet(req, resp);
        }
    }

    @Override
    public void init() {
        usersRepository = new UsersRepositoryJdbcImpl(ConnectionCreator.getConnection());
    }
}
