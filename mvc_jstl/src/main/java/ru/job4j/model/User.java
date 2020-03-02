package ru.job4j.model;

import java.util.Objects;

public class User {

    private String id;

    private String name;

    private String login;

    private String password;

    private String email;

    private String createDate;

    private String image;

    private String roleName;

    private String country;

    private String city;

    public User(String id, String name, String login, String password,
                String email, String createDate, String image,
                String roleName, String country, String city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.image = image;
        this.roleName = roleName;
        this.country = country;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getImage() {
        return image;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(email, user.email)
                && Objects.equals(createDate, user.createDate)
                && Objects.equals(image, user.image)
                && Objects.equals(roleName, user.roleName)
                && Objects.equals(country, user.country)
                && Objects.equals(city, user.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                login,
                password,
                email,
                createDate,
                image,
                roleName,
                country,
                city
        );
    }

    @Override
    public String toString() {
        return String.format(
                "User: id=%s, name=%s, login=%s, password=%s, email=%s, createDate=%s, rolename=%s, country=%s, city=%s",
                id,
                name,
                login,
                password,
                email,
                createDate,
                roleName,
                country,
                city
        );

    }
}
