package ru.job4j.dream.servlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServletTest {

    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setup() {
        pool.setDriverClassName("org.h2.Driver");
        pool.setUrl("jdbc:h2:./testdb;MODE=PostgreSQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE");
        pool.setUsername("");
        pool.setPassword("");
    }

    @Test
    public void whenCreatePost() {
        try {
            DbStore store = (DbStore) DbStore.instOf();
            store.setPool(pool);
            store.reset("post");
            store.reset("candidate");
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            when(req.getParameter("id")).thenReturn("0");
            when(req.getParameter("name")).thenReturn("name of new post");
            when(req.getParameter("description")).thenReturn("d");
            new PostServlet().doPost(req, resp);
            Post post = DbStore.instOf().findPostById(1);
            assertThat(post.getName(), is("name of new post"));
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}