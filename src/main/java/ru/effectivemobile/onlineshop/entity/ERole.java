package ru.effectivemobile.onlineshop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ERole {
    @JsonProperty("user")
    USER,
    @JsonProperty("admin")
    ADMIN;
}
