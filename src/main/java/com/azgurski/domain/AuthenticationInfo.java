package com.azgurski.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AuthenticationInfo {

//    @JsonIgnore //убирает из итогового JSON поля
    // @JsonView // для отображения позже
    private String emailUserAuth;

    @JsonIgnore
    private String passwordUserAuth;
}
