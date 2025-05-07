package ru.vsu.cs.booking;

import ru.vsu.cs.framework.application.Application;
import ru.vsu.cs.framework.controller.Controller;
import ru.vsu.cs.framework.controller.HttpRequest;
import ru.vsu.cs.framework.controller.HttpResponse;

public class BookingController extends Controller {
    private final BookingRepository bookingRepository;

    public BookingController() {
        super("/api/v1/booking");
        addGetPath("/all", this::getAll);
        bookingRepository = Application.getApplication().getBean(BookingRepository.class);
    }

    public HttpResponse getAll(HttpRequest request) {
        return new HttpResponse(HttpResponse.OK, bookingRepository.findAll());
    }
}
