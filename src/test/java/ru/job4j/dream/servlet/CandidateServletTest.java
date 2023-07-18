package ru.job4j.dream.servlet;

import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.DbStore;
import ru.job4j.dream.store.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CandidateServletTest {
    private static final String DB_URL = "jdbc:hsqldb:mem:testdb";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    Connection conn = null;

    @Test
    public void whenCreatePost() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database!");
            Store store = DbStore.instOf();
            store.reset("post");
            store.reset("candidate");
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            when(req.getParameter("id")).thenReturn("0");
            when(req.getParameter("name")).thenReturn("name of new candidate");
            when(req.getParameter("description")).thenReturn("d");
            new CandidateServlet().doPost(req, resp);
            Candidate cand = DbStore.instOf().findCandidateById(1);
            assertThat(cand.getName(), is("name of new candidate"));
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}