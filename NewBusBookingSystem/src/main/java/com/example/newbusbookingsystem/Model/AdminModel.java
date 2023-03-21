package com.example.newbusbookingsystem.Model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class AdminModel {
    @Id
    @Column(unique = true)
    private Long adminId;

    private String passcode;

    public AdminModel(String passcode) {
        this.passcode = passcode;
    }

    public AdminModel(){

    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "adminId=" + adminId +
                ", passcode='" + passcode + '\'' +
                '}';
    }
}
