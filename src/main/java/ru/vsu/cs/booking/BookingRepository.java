package ru.vsu.cs.booking;

import java.util.List;

public interface BookingRepository {
    List<Booking> findAll();

    Booking findById(int id);

    void save(Booking booking);

    void delete(int id);
}
