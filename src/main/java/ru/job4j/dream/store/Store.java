package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {

    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

    void save(Post post);

    void save(Candidate candidate);

    void save(User user);

    City save(City city);

    Post findPostById(int id);

    Candidate findCandidateById(int id);

    User findUserByEmail(String email);

    void reset(String tableName);

    Collection<Post> getRecentPosts();

    Collection<Candidate> getRecentCandidates();

    Collection<City> findAllCities();

    int getCityId(String cityName);
}