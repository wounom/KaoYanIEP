package com.wounom.kaoyaniep.entity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/30 15:53
 */
public class User {

    private long id;
    private String username;
    private String password;
    private String email;
    private String code;
    private String salt;
    private long is_Valid;
    private LocalDateTime active_Time;

    private String sex;
    private String signature;
    private String target_Unversity;

    private Date birthDay;

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTarget_Unversity() {
        return target_Unversity;
    }

    public void setTarget_Unversity(String target_Unversity) {
        this.target_Unversity = target_Unversity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public long getIs_Valid() {
        return is_Valid;
    }

    public void setIs_Valid(long is_Valid) {
        this.is_Valid = is_Valid;
    }


    public LocalDateTime getActive_Time() {
        return active_Time;
    }

    public void setActive_Time(LocalDateTime active_Time) {
        this.active_Time = active_Time;
    }

}

