package ru.itis.servlets;

import ru.itis.entities.Role;
import ru.itis.entities.User;
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
import java.util.Optional;
import java.util.Properties;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
        Optional<User> u = usersRepositoryJdbc.findOneByEmail(email);
        User user;
        HttpSession session = req.getSession();
        if (u.isPresent()) {
            user = u.get();
            session.setAttribute("firstname", user.getFirstName());
            session.setAttribute("lastname", user.getLastName());
            if (user.getPassword().equals(password)) {
                resp.sendRedirect("/profile");
            }
        } else {
            doGet(req, resp);
        }
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
    }
}