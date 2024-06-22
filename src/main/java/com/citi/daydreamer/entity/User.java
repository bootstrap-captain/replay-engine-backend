package com.citi.daydreamer.entity;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String username;
    private String password;
    private String type;
}
