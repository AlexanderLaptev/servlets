package ru.vsu.cs.category;

import java.util.List;

public class MockCategoryRepository implements CategoryRepository {
    @Override
    public List<Category> findAll() {
        return List.of(
                new Category(1, "test 1"),
                new Category(2, "test 2")
        );
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void save(Category category) {
    }

    @Override
    public void delete(int id) {
    }
}
