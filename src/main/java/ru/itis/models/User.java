package ru.itis.models;

import java.util.List;

public class User {
    private Long id;
    private String nickname;
    private String email;
    private String name;
    private String last_name;
    private String patronymic;
    private String login;
    private String password;
    private Role role;
    private List<Event> created_events;
    private List<Event> subscribed_events;
    private String data_of_registration;
    private boolean registered;

    public User(){}

    public User(Long id, String nickname, String email, String name, String last_name, String patronymic, String login, String password, Role role, List<Event> created_events, List<Event> subscribed_events, String data_of_registration, boolean registered) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
        this.role = role;
        this.created_events = created_events;
        this.subscribed_events = subscribed_events;
        this.data_of_registration = data_of_registration;
        this.registered = registered;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPatronymic() {
        return patronymic;
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

    public List<Event> getCreated_events() {
        return created_events;
    }

    public List<Event> getSubscribed_events() {
        return subscribed_events;
    }

    public String getData_of_registration() {
        return data_of_registration;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public void setCreated_events(List<Event> created_events) {
        this.created_events = created_events;
    }

    public void setSubscribed_events(List<Event> subscribed_events) {
        this.subscribed_events = subscribed_events;
    }

    public void setData_of_registration(String data_of_registration) {
        this.data_of_registration = data_of_registration;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
