package ru.vsu.cs.event;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private int organizerId;
    private String title;
    private String description;
    private int categoryId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private int capacity;
    private boolean isPublished;
}
