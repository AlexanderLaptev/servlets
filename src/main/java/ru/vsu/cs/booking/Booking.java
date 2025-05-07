package ru.vsu.cs.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
    private int userId;
    private int eventId;
    private LocalDateTime bookingTime;
    private int quantity;
    private BookingStatus status;
    private BigDecimal ticketPrice;
    private TicketType ticketType;
}
