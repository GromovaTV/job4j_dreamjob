package ru.job4j.dream.servlet;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ArrayList<Post> posts = new ArrayList<>(DbStore.instOf().getRecentPosts());
        ArrayList<Candidate> candidates = new ArrayList<>(DbStore.instOf().getRecentCandidates());
        ArrayList<City> cities = new ArrayList<>(DbStore.instOf().findAllCities());
        System.out.println(candidates);
        req.setAttribute("posts", posts);
        req.setAttribute("candidates", candidates);
        req.setAttribute("citiesList", cities);
        req.getRequestDispatcher("/welcome/index.jsp").forward(req, resp);
    }
}