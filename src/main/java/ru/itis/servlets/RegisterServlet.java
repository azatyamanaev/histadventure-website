package ru.itis.servlets;

import ru.itis.entities.Role;
import ru.itis.entities.User;
import ru.itis.models.EventsModel;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet(name = "register", urlPatterns = "/histadventure_website/register")
public class RegisterServlet extends HttpServlet {
    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/histadventure_website/view/profile.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(email, firstname, lastname, login, password, Role.VERIFIED, null);
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
        usersRepositoryJdbc.save(user);
        doGet(req, resp);
    }

    @Override
    public void init() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("db.properties"));
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
