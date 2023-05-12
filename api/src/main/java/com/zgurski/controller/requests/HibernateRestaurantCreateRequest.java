package com.zgurski.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class HibernateRestaurantCreateRequest {

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 symbols.")
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String street_house;

    @NotNull
    private String city;

    @NotNull
    private String postcode;

    @NotNull
    private String country;

    @NotNull
    private Float longitude;

    @NotNull
    private Float latitude;

    @NotNull
    private String image_URL;

    @NotNull
    private String role_id;

    @NotNull
    private String website;

    private String email_user_auth;

    private String password_user_auth;

    private String capacity;
}