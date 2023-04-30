package com.azgurski.repository.impl;

import com.azgurski.domain.Restaurant;
import com.azgurski.repository.RestaurantRepository;
import com.azgurski.rowmapper.RestaurantRowMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RestaurantRowMapper restaurantRowMapper;


//    private final DatabaseProperties properties;
    private final Logger logger = Logger.getLogger(RestaurantRepositoryImpl.class);


    @Override
    public List<String> getRestaurantEmails() {
        return null;
    }

    //    @Override
//    public List<Restaurant> findAll() {
//        logger.info("Start of findAll Restaurants method");
//
//        final String findAllQuery = "select * from restaurants order by name asc";
//
//        List<Restaurant> resultFindAll = new ArrayList<>();
//
//        registerDriver();
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement(findAllQuery);
//             ResultSet rs = statement.executeQuery()
//        ) {
//
//            while (rs.next()) {
//                resultFindAll.add(parseResultSet(rs));
//            }
//
//            logger.info("End of findAll Restaurants method");
//
//            return resultFindAll;
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("SQL Issues!");
//        }
//    }
//
//    @Override
//    public List<String> getRestaurantEmails() {
//        logger.info("Start of getRestaurantEmails method");
//
//        final String getRestaurantEmails = "select email from restaurants order by email asc";
//
//        List<String> resultRestaurantEmails = new ArrayList<>();
//
//        registerDriver();
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(getRestaurantEmails);
//             ResultSet rs = preparedStatement.executeQuery()
//        ) {
//
//            while (rs.next()) {
//                resultRestaurantEmails.add(rs.getString(EMAIL));
//            }
//
//            logger.info("End of getRestaurantEmails method");
//
//            return resultRestaurantEmails;
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("SQL Issues!");
//        }
//    }

//    private Connection getConnection() {
//        String jdbcURL = StringUtils.join(properties.getUrl(), properties.getPort(), properties.getName());
//        try {
//            return DriverManager.getConnection(jdbcURL, properties.getLogin(), properties.getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private void registerDriver() {
//        try {
//            Class.forName(properties.getDriverName());
//        } catch (ClassNotFoundException e) {
//            System.err.println("JDBC Driver Cannot be loaded!");
//            throw new RuntimeException("JDBC Driver Cannot be loaded!");
//        }
//    }
//
//    private Restaurant parseResultSet(ResultSet rs) {
//        Restaurant restaurant;
//
//        try {
//            restaurant = Restaurant.builder()
//                    .restaurant_id(rs.getLong(RESTAURANT_ID))
//                    .name(rs.getString(NAME))
//                    .email(rs.getString(EMAIL))
//                    .phone(rs.getString(PHONE))
//                    .street_house(rs.getString(STREET_HOUSE))
//                    .city(rs.getString(CITY))
//                    .postcode(rs.getString(POSTCODE))
//                    .country(rs.getString(COUNTRY))
//                    .longitude(rs.getFloat(LONGITUDE))
//                    .latitude(rs.getFloat(LATITUDE))
//                    .image_URL(rs.getString(IMAGE_URL))
//                    .website(rs.getString(WEBSITE))
//                    .build();
//
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        return restaurant;
//    }

    @Override
    public List<Restaurant> findAll() {
        logger.info("Start of findAll Restaurants method");

        List<Restaurant> allRestaurants = jdbcTemplate.query("select * from restaurants order by name asc",
                restaurantRowMapper);

        logger.info("End of findAll Restaurants method");

        return allRestaurants;

//
//        final String findAllQuery = "select * from restaurants order by name asc";
//
//        List<Restaurant> resultFindAll = new ArrayList<>();
    }

    @Override
    public Restaurant findOne(Long restaurant_id) {
            return jdbcTemplate.query("select * from restaurants where restaurant_id = ?", new Object[] {restaurant_id},
                    new BeanPropertyRowMapper<>(Restaurant.class)).stream().findAny().orElse(null);
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

    @Override
    public boolean support(String param) {
        return param.equalsIgnoreCase("jdbc");
    }
}