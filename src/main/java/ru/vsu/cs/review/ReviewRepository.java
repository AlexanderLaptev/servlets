package ru.vsu.cs.review;

import java.util.List;

public interface ReviewRepository {
    List<Review> findAll();

    Review findById(int id);

    void save(Review review);

    void delete(int id);
}
