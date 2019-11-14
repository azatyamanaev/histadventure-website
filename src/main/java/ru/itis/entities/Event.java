package ru.itis.entities;

import java.util.List;

public class Event {
    private Long id;
    private String name;
    private String description;
    private List<User> participants;
    private int capacity;
    private String host;
    private boolean active;
    private String place;
    private String timeStart;
    private String timeEnd;
    private List<User> subscribedUsers;

    public Event() {}

    public Event(Long id, String name, String description, List<User> participants, int capacity, String host, boolean active, String place, String timeStart, String timeEnd, List<User> subscribedUsers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.participants = participants;
        this.capacity = capacity;
        this.host = host;
        this.active = active;
        this.place = place;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.subscribedUsers = subscribedUsers;
    }

    public Event(String name, String description, List<User> participants, int capacity, String host, boolean active, String place, String timeStart, String timeEnd, List<User> subscribedUsers) {
        this.name = name;
        this.description = description;
        this.participants = participants;
        this.capacity = capacity;
        this.host = host;
        this.active = active;
        this.place = place;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.subscribedUsers = subscribedUsers;
    }

    public Event(Long id, String name, String description, int capacity, String host, boolean active, String place, String timeStart, String timeEnd) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.host = host;
        this.active = active;
        this.place = place;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getHost() {
        return host;
    }

    public boolean isActive() {
        return active;
    }

    public String getPlace() {
        return place;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public List<User> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setSubscribedUsers(List<User> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }
}
