package ru.itis.entities;

import java.util.List;

public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role;
    private List<Event> subscribedEvents;

    public User(){}

    public User(Long id, String email, String firstName, String lastName, String login, String password, Role role, List<Event> subscribedEvents) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
        this.subscribedEvents = subscribedEvents;
    }

    public User(String email, String firstName, String lastName, String login, String password, Role role, List<Event> subscribedEvents) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
        this.subscribedEvents = subscribedEvents;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setSubscribed_events(List<Event> subscribedEvents) {
        this.subscribedEvents = subscribedEvents;
    }
}
