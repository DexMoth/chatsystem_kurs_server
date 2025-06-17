package com.edu.chatsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

import java.util.Objects;

@Entity
@Table(name = "users")
@Data
public class UserEntity extends BaseEntity {
    private String login;
    private String name;
    private String password;
    private String phone;
    private String reportCardNumber;

    public UserEntity() {
    }

    public UserEntity(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }
    public UserEntity(String login, String name, String password, String phone, String reportCardNumber) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.reportCardNumber = reportCardNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportCardNumber() {
        return reportCardNumber;
    }
    public void setReportCardNumber(String reportCardNumber) {
        this.reportCardNumber = reportCardNumber;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final UserEntity other = (UserEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getName(), login)
                && Objects.equals(other.getName(), name)
                && Objects.equals(other.getReportCardNumber(), reportCardNumber)
                && Objects.equals(other.getPhone(), phone);
    }
}
