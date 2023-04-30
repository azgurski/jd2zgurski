package com.azgurski.rowmapper;

import com.azgurski.domain.Restaurant;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.azgurski.repository.columns.RestaurantColumns.CITY;
import static com.azgurski.repository.columns.RestaurantColumns.COUNTRY;
import static com.azgurski.repository.columns.RestaurantColumns.EMAIL;
import static com.azgurski.repository.columns.RestaurantColumns.IMAGE_URL;
import static com.azgurski.repository.columns.RestaurantColumns.LATITUDE;
import static com.azgurski.repository.columns.RestaurantColumns.LONGITUDE;
import static com.azgurski.repository.columns.RestaurantColumns.NAME;
import static com.azgurski.repository.columns.RestaurantColumns.PHONE;
import static com.azgurski.repository.columns.RestaurantColumns.POSTCODE;
import static com.azgurski.repository.columns.RestaurantColumns.RESTAURANT_ID;
import static com.azgurski.repository.columns.RestaurantColumns.STREET_HOUSE;
import static com.azgurski.repository.columns.RestaurantColumns.WEBSITE;

@Component
public class RestaurantRowMapper implements RowMapper<Restaurant> {
    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Restaurant restaurant;

        restaurant = Restaurant.builder()
                .restaurant_id(rs.getLong(RESTAURANT_ID))
                .name(rs.getString(NAME))
                .email(rs.getString(EMAIL))
                .phone(rs.getString(PHONE))
                .street_house(rs.getString(STREET_HOUSE))
                .city(rs.getString(CITY))
                .postcode(rs.getString(POSTCODE))
                .country(rs.getString(COUNTRY))
                .longitude(rs.getFloat(LONGITUDE))
                .latitude(rs.getFloat(LATITUDE))
                .image_URL(rs.getString(IMAGE_URL))
                .website(rs.getString(WEBSITE))
                .build();

        return restaurant;
    }
}
