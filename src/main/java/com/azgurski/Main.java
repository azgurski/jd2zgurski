package com.azgurski;

import com.azgurski.domain.Slot;
import com.azgurski.repository.RestaurantRepository;
import com.azgurski.repository.SlotRepository;
import com.azgurski.service.RestaurantService;
import com.azgurski.service.SlotService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                "com.azgurski");

        // For entity 'Restaurant' realised methods : findAll(), getRestaurantEmails().

        RestaurantRepository restaurantRepository = applicationContext.getBean(
                "restaurantRepositoryImpl", RestaurantRepository.class);
        RestaurantService restaurantService = applicationContext.getBean(
                "restaurantServiceImpl", RestaurantService.class);

        logger.info(restaurantService.findAll());

        // for send email marketing for example
        logger.info(restaurantService.getRestaurantEmails());

        // For entity 'Slot' realised methods :
        // findOne(by Slot_Id), showAvailableSlots(by Date, by Restaurant_ID), create(), update(), delete().

        SlotRepository slotRepository = applicationContext.getBean("slotRepositoryImpl", SlotRepository.class);
        SlotService slotService = applicationContext.getBean("slotServiceImpl", SlotService.class);

        logger.info(slotService.findOne(50L));

        // if we need to see all availabilities for day and for restaurant
        logger.info(slotService.showAvailableSlotsDayRestaurant(
                java.sql.Date.valueOf("2023-03-07"), 4L));

        logger.info(slotService.create(
                Slot.builder().restaurant_id(4L)
                        .day_calendar(java.sql.Date.valueOf("2023-03-07"))
                        .time_slot(java.sql.Time.valueOf("13:30:00"))
                        .is_available(true)
                        .build()));

        // for example if restaurant wants set one of his slots available
        logger.info(slotService.update(
                Slot.builder().slot_id(5L).is_available(true).build()));

        logger.info(slotService.delete(29L));
    }
}