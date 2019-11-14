package ru.itis.entities;

import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private Role role;
    private List<Event> subscribedEvents;
    private List<Event> createdEvents;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String login, String password, Role role, List<Event> subscribedEvents, List<Event> createdEvents) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.subscribedEvents = subscribedEvents;
        this.createdEvents = createdEvents;
    }

    public User(String firstName, String lastName, String email, String login, String password, Role role, List<Event> subscribedEvents, List<Event> createdEvents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.subscribedEvents = subscribedEvents;
        this.createdEvents = createdEvents;
    }

    public User(String firstName, String lastName, String email, String login, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String firstName, String lastName, String email, String login, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<Event> getSubscribedEvents() {
        return subscribedEvents;
    }

    public List<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSubscribedEvents(List<Event> subscribedEvents) {
        this.subscribedEvents = subscribedEvents;
    }

    public void setCreatedEvents(List<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }
}
