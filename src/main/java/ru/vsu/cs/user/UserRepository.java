package ru.vsu.cs.user;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(int id);

    void save(User user);

    void delete(int id);
}
