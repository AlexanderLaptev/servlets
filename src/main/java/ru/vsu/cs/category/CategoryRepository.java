package ru.vsu.cs.category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();

    Category findById(int id);

    void save(Category category);

    void delete(int id);
}
