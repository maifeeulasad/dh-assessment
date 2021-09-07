package com.mua.dh.model;

public enum UserType {
    USER(1L),
    ADMIN(0L);

    private final Long value;

    UserType(Long value){
        this.value=value;
    }
}
