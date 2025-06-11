package com.edu.chatsystem.dto;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String password;
    private String phone;
    private String reportCardNumber;

    public UserDto() {}
    public UserDto(Long id, String login, String name, String phone, String reportCardNumber) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.phone = phone;
        this.reportCardNumber = reportCardNumber;
    }
    public UserDto(Long id, String login, String name, String password, String phone, String reportCardNumber) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.reportCardNumber = reportCardNumber;
    }
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getReportCardNumber() {
        return reportCardNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setReportCardNumber(String reportCardNumber) {
        this.reportCardNumber = reportCardNumber;
    }
}
