package com.zgurski.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class RestaurantFieldsGenerator {

    public String generateEmail(String postcode) {
        return postcode + "_" + RandomStringUtils.random(10, true, true) + "_user@gmail.com";
    }

    public String generatePassword() {
        return RandomStringUtils.random(20, true, true);
    }
}