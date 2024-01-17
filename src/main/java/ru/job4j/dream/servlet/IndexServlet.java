package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class IndexServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOG.info("Start Index Servlet GET");
        ArrayList<Post> posts = new ArrayList<>(DbStore.instOf().getRecentPosts());
        ArrayList<Candidate> candidates = new ArrayList<>(DbStore.instOf().getRecentCandidates());
        ArrayList<City> cities = new ArrayList<>(DbStore.instOf().findAllCities());
        LOG.info(String.valueOf(candidates));
        req.setAttribute("posts", posts);
        req.setAttribute("candidates", candidates);
        req.setAttribute("citiesList", cities);
        req.getRequestDispatcher("/welcome/index.jsp").forward(req, resp);
        LOG.info("Finish Index Servlet GET");
    }
}