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

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean auth = (Boolean) session.getAttribute("auth");
        if (auth != null && auth) {
            resp.sendRedirect("/profile");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> u = usersRepository.findOneByEmail(email);
        User user;
        HttpSession session = req.getSession();
        if (u.isPresent()) {
            user = u.get();
            //PasswordEncoder encoder = new BCryptPasswordEncoder();
            int hash = password.hashCode();
            if (user.getPassword().equals(Integer.toString(hash))) {
                session.setAttribute("user", user);
                session.setAttribute("auth", true);
                resp.sendRedirect("/profile");
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/register");
        }
    }

    @Override
    public void init() {
        usersRepository = new UsersRepositoryJdbcImpl(ConnectionCreator.getConnection());
    }
}
