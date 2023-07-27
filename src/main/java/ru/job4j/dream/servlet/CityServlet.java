package ru.job4j.dream.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.DbStore;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CityServlet  extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        City city = GSON.fromJson(req.getReader(), City.class);
        City save = DbStore.instOf().save(new City(0, city.getName()));
        if (save != null) {
            System.out.println("City:" + save);
            resp.setContentType("application/json; charset=utf-8");
            OutputStream output = resp.getOutputStream();
            String json = GSON.toJson(save);
            output.write(json.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
            req.setAttribute("city", save.getName());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(DbStore.instOf().findAllCities());
        output.write(json.getBytes(StandardCharsets.UTF_8));
        req.setAttribute("citiesList", DbStore.instOf().findAllCities());
        output.flush();
        output.close();
    }
}