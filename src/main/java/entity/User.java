package entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class User {
    int id;
    String login;
    String password;
    String name;
    String region;
    boolean gender;
    String comment;

    public User() {

    }

    public User(int id, String login, String password, String name, String region, boolean gender, String comment) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.region = region;
        this.gender = gender;
        this.comment = comment;
    }

    public User(String login, String password, String name, String region, boolean gender, String comment) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.region = region;
        this.gender = gender;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                gender == user.gender &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(region, user.region) &&
                Objects.equals(comment, user.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, region, gender, comment);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", gender=" + gender +
                ", comment='" + comment + '\'' +
                '}';
    }
}
