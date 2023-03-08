package com.azgurski.repository;

import com.azgurski.configuration.DatabaseProperties;
import com.azgurski.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

@Repository
@RequiredArgsConstructor
@Primary
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final DatabaseProperties properties;
    private final Logger logger = Logger.getLogger(RestaurantRepositoryImpl.class);

    @Override
    public List<Restaurant> findAll() {
        logger.info("Start of findAll Restaurants method");

        final String findAllQuery = "select * from restaurants order by restaurant_id asc";

        List<Restaurant> resultFindAll = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllQuery);
             ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {
                resultFindAll.add(parseResultSet(rs));
            }

            logger.info("End of findAll Restaurants method");

            return resultFindAll;
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<String> getRestaurantEmails() {
        logger.info("Start of getRestaurantEmails method");

        final String getRestaurantEmails = "select email from restaurants order by email asc";

        List<String> resultRestaurantEmails = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getRestaurantEmails);
             ResultSet rs = preparedStatement.executeQuery()
        ) {

            while (rs.next()) {
                resultRestaurantEmails.add(rs.getString(EMAIL));
            }

            logger.info("End of getRestaurantEmails method");

            return resultRestaurantEmails;
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("SQL Issues!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(properties.getUrl(), properties.getPort(), properties.getName());
        try {
            return DriverManager.getConnection(jdbcURL, properties.getLogin(), properties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerDriver() {
        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Restaurant parseResultSet(ResultSet rs) {
        Restaurant restaurant;

        try {
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

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return restaurant;
    }

    @Override
    public Restaurant findOne(Long id) {
        return null;
    }

    @Override
    public Restaurant create(Restaurant object) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}