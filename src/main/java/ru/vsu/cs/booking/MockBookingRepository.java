package ru.vsu.cs.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MockBookingRepository implements BookingRepository {
    @Override
    public List<Booking> findAll() {
        var b1 = new Booking(
                1,
                2,
                LocalDateTime.now(),
                2,
                BookingStatus.CONFIRMED,
                BigDecimal.valueOf(129.99),
                TicketType.GENERAL_ADMISSION
        );
        var b2 = new Booking(
                3,
                4,
                LocalDateTime.now(),
                4,
                BookingStatus.REFUNDED,
                BigDecimal.valueOf(199.99),
                TicketType.GENERAL_ADMISSION
        );
        var b3 = new Booking(
                5,
                6,
                LocalDateTime.now(),
                1,
                BookingStatus.CANCELLED,
                BigDecimal.valueOf(220),
                TicketType.VIP
        );
        return List.of(b1, b2, b3);
    }

    @Override
    public Booking findById(int id) {
        return null;
    }

    @Override
    public void save(Booking booking) {

    }

    @Override
    public void delete(int id) {

    }
}
