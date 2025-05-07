package ru.vsu.cs.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
    private final int userId;
    private final int eventId;
    private final LocalDateTime bookingTime;
    private final int quantity;
    private final BookingStatus status;
    private final BigDecimal ticketPrice;
    private final TicketType ticketType;

    public Booking(
            int userId,
            int eventId,
            LocalDateTime bookingTime,
            int quantity,
            BookingStatus status,
            BigDecimal ticketPrice,
            TicketType ticketType
    ) {
        this.userId = userId;
        this.eventId = eventId;
        this.bookingTime = bookingTime;
        this.quantity = quantity;
        this.status = status;
        this.ticketPrice = ticketPrice;
        this.ticketType = ticketType;
    }

    public int getUserId() {
        return userId;
    }

    public int getEventId() {
        return eventId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
