package ru.vsu.cs.payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll();

    Payment findById(int id);

    void save(Payment payment);

    void delete(int id);
}
