package ru.itis.servlets;

import ru.itis.entities.Role;
import ru.itis.entities.User;
import ru.itis.models.EventsModel;
import ru.itis.models.Validator;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private Connection connection;
    private UsersRepositoryJdbcImpl usersRepositoryJdbc;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //PasswordEncoder encoder = new BCryptPasswordEncoder();
        //String hash = encoder.encode(password);
        User user = new User(firstname, lastname, email, login, password, Role.VERIFIED);
        usersRepositoryJdbc.save(user);
        HttpSession session = req.getSession();
        session.setAttribute("firstname", user.getFirstName());
        session.setAttribute("lastname", user.getLastName());
        session.setAttribute("createdEvents", user.getCreatedEvents());
        session.setAttribute("subscribedEvents", user.getSubscribedEvents());
        session.setAttribute("auth", true);
        resp.sendRedirect("/profile");
        //if (Validator.validate(firstname, Validator.nameRegex) && Validator.validate(lastname, Validator.nameRegex) && Validator.validate(email, Validator.emailRegex) && Validator.validate(login, Validator.loginRegex) && Validator.validate(password, Validator.passwordRegex)) {
        //} else {
        //    doGet(req, resp);
        //}
    }

    @Override
    public void init() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/home/monitor/Рабочий стол/Project(dev1)/histadventure-website/src/main/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String url = properties.getProperty("db.url");

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
    }
}
