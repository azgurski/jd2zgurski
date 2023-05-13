package com.zgurski.controller.requests;

import com.zgurski.domain.Capacity;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "RestaurantCreateRequest")
public class HibernateRestaurantCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "La Fourchette", type = "string", description = "restaurant name")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 symbols.")
    @NotNull
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "restaurant@gmail.com", type = "string", description = "restaurant email")
    @Email
    @NotNull
    private String email;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "+XXXXXXXXXX", type = "string", description = "restaurant phone")
    @Size(min = 7, max = 50)
    @NotNull
    private String phone;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Berliner Str. 10", type = "string", description = "restaurant street, house number")
    @Size(min = 2, max = 200)
    @NotNull
    private String street_house;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Berlin", type = "string", description = "restaurant city")
    @Size(min = 2, max = 50)
    @NotNull
    private String city;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "78000", type = "string", description = "restaurant postcode")
    @Size(min = 2, max = 10)
    @NotNull
    private String postcode;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Germany", type = "string", description = "restaurant country")
    @Size(min = 2, max = 30)
    @NotNull
    private String country;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "55.55555", type = "float", description = "restaurant longitude")
    @NotNull
    private Float longitude;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "44.44444", type = "float", description = "restaurant latitude")
    @NotNull
    private Float latitude;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "https://imageurl.com", type = "string", description = "restaurant image_url")
    @Size(min = 2, max = 500)
    @NotNull
    private String image_URL;

    @NotNull
    private String role_id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "https://website.com", type = "string", description = "restaurant website")
    @Size(min = 2, max = 100)
    @NotNull
    private String website;

    private String email_user_auth;

    private String password_user_auth;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "NOT_SELECTED", type = "Capacity", description = "restaurant capacity")
    @NotNull
    private Capacity capacity;

//    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "NOT_SELECTED", type = "string", description = "capacity")
//    @Size(min = 2, max = 20)
//    private String capacity;
}