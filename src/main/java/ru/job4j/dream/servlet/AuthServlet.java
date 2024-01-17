package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AuthServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Start Auth Servlet POST");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = DbStore.instOf().findUserByEmail(req.getParameter("email"));
        if (user != null && user.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        LOG.info("Finish Auth Servlet POST");
    }
}