package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Start Logout Servlet");
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("login.jsp").forward(req, resp);
        LOG.info("Finish Logout Servlet");
    }
}