package ru.job4j.dream.store;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class DbStoreTest {
    @Before
    public void reset() {
        Store store = DbStore.instOf();
        store.reset("post");
        store.reset("candidate");
    }

    @Test
    public void whenCreatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job");
        store.save(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdPost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postUpd = new Post(post.getId(), "Java Junior Job");
        store.save(postUpd);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is(postUpd.getName()));
    }

    @Test
    public void whenUpdCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java Job");
        store.save(candidate);
        Candidate candidateUpd = new Candidate(candidate.getId(), "Java Junior Job");
        store.save(candidateUpd);
        Candidate postInDb = store.findCandidateById(candidate.getId());
        assertThat(postInDb.getName(), is(candidateUpd.getName()));
    }

    @Test
    public void whenFindAllPost() {
        Store store = DbStore.instOf();
        Post post1 = new Post(0, "Java Job");
        store.save(post1);
        Post post2 = new Post(0, "Java Job");
        store.save(post2);
        var posts = store.findAllPosts();
        posts.forEach(System.out::println);
        assertThat(posts, is(List.of(post1, post2)));
    }

    @Test
    public void whenFindAllCandidates() {
        Store store = DbStore.instOf();
        Candidate candidate1 = new Candidate(0, "Java Job");
        store.save(candidate1);
        Candidate candidate2 = new Candidate(0, "Java Job");
        store.save(candidate2);
        var candidates = store.findAllCandidates();
        assertThat(candidates, is(List.of(candidate1, candidate2)));
    }
}