package com.example.WebSocketCoummunication.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_info")
public class UserInfo {

    //TODO: Zmiana country i gender na Enum
    //TODO: Zmiana kolumn w SQL country i gender na Enum

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick")
    private String nick;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "age")
    private String age;

    public UserInfo() {
        super();
    }

    public UserInfo(String nick,
                    String password,
                    String email,
                    String phone,
                    Country country,
                    Gender gender,
                    String age) {

        this.nick = nick;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.gender = gender;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
