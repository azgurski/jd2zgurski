package com.zgurski.repository.impl;

import com.zgurski.domain.Slot;
import com.zgurski.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.zgurski.repository.columns.SlotColumns.DAY_CALENDAR;
import static com.zgurski.repository.columns.SlotColumns.IS_AVAILABLE;
import static com.zgurski.repository.columns.SlotColumns.RESERVATION_ID;
import static com.zgurski.repository.columns.SlotColumns.RESTAURANT_ID;
import static com.zgurski.repository.columns.SlotColumns.SLOT_ID;
import static com.zgurski.repository.columns.SlotColumns.TIME_SLOT;

@Repository
@RequiredArgsConstructor
@Primary
public class SlotRepositoryImpl implements SlotRepository {

//    private final DatabaseProperties properties;
    private final Logger logger = Logger.getLogger(SlotRepositoryImpl.class);

    @Override
    public Slot create(Slot slot) {
        return null;
    }

    @Override
    public Slot findOne(Long slot_id) {
        return null;
    }

    @Override
    public Slot update(Slot slot) {
        return null;
    }

    @Override
    public Long delete(Long slot_id) {
        return null;
    }

    @Override
    public List<String> showAvailableSlotsRestaurant(Date date, Long restaurant_id) {
        return null;
    }

    //    @Override
//    public Slot findOne(Long slot_id) {
//
//        logger.info("Start of findOne Slot method");
//
//        final String findOneQuery = "select * from slots where slot_id = ?";
//
//        List<Slot> resultFindOneList = new ArrayList<>();
//
//        registerDriver();
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(findOneQuery)
//        ) {
//            preparedStatement.setLong(1, slot_id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                resultFindOneList.add(parseResultSet(rs));
//            }
//
//            rs.close();
//            preparedStatement.close();
//
//            logger.info("End of findOne Slot method");
//
//            return resultFindOneList.get(0);
//
//        } catch (IndexOutOfBoundsException ex) {
//            String errorMessage = String.format("Slot id# %s not found", slot_id);
//            logger.error(errorMessage);
//            throw new EntityNotFoundException(errorMessage);
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("SQL Issues!");
//        }
//    }
//
//    @Override
//    public List<String> showAvailableSlotsRestaurant(Date date, Long restaurant_id) {
//
//        logger.info("Start of showAvailableSlotsDayRestaurant method");
//
//        final String getAvailableSlotsDayRestaurant = "select restaurant_id, day_calendar, time_slot " +
//                "from slots where restaurant_id=? and day_calendar=? and is_available='true' order by time_slot asc";
//
//        List<String> resultAvailableSlots = new ArrayList<>();
//
//        registerDriver();
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(getAvailableSlotsDayRestaurant)
//        ) {
//
//            preparedStatement.setLong(1, restaurant_id);
//            preparedStatement.setDate(2, date);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                resultAvailableSlots.add(rs.getTime(TIME_SLOT).toString());
//            }
//
//            rs.close();
//            preparedStatement.close();
//
//            logger.info("End of showAvailableSlotsRestaurant method");
//
//            return resultAvailableSlots;
//
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("SQL Issues!");
//        }
//    }

//    @Override
//    public Slot create(Slot slot) {
//
//        logger.info("Start of Slot create method");
//
//        final String insertSlotQuery = "insert into slots (slot_id, restaurant_id, day_calendar, is_available, " +
//                "created, changed, time_slot) values (default, ?, ?, ?, NOW(), NOW(), ?)";
//        final String lastSlotIdQuery = "select max(slot_id) from slots";
//
//        registerDriver();
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(insertSlotQuery)
//        ) {
//            preparedStatement.setLong(1, slot.getRestaurant_id());
//            preparedStatement.setDate(2, slot.getDay_calendar());
//            preparedStatement.setBoolean(3, slot.getIs_available());
//            preparedStatement.setTime(4, slot.getTime_slot());
//
//            preparedStatement.executeUpdate();
//
//            PreparedStatement lastSlotIdPreparedStatement = connection.prepareStatement(lastSlotIdQuery);
//            ResultSet rs = lastSlotIdPreparedStatement.executeQuery();
//
//            while (rs.next()) {
//                slot.setSlot_id(rs.getLong(1)); // '1' as columnIndex
//            }
//
//            rs.close();
//            lastSlotIdPreparedStatement.close();
//            preparedStatement.close();
//
//            logger.info("End of Slot create method");
//
//            return findOne(slot.getSlot_id());
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("SQL Issues!");
//        }
//    }

//    @Override
//    public Slot update(Slot slot) {
//
//        logger.info("Start of Slot update method");
//
//        Slot slotFromDBById = findOne(slot.getSlot_id());
//
//        final String updateSlotQuery = "update slots set " +
//                "restaurant_id = ?, day_calendar = ?, is_available = ?, changed = NOW()," +
//                "reservation_id = ?, time_slot = ? where slot_id = ?";
//
//        registerDriver();
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(updateSlotQuery)
//        ) {
//            preparedStatement.setLong(1,
//                    slot.getRestaurant_id() != null ? slot.getRestaurant_id() : slotFromDBById.getRestaurant_id());
//            preparedStatement.setDate(2,
//                    slot.getDay_calendar() != null ? slot.getDay_calendar() : slotFromDBById.getDay_calendar());
//            preparedStatement.setBoolean(3,
//                    slot.getIs_available() != null ? slot.getIs_available() : slotFromDBById.getIs_available());
//            preparedStatement.setLong(4,
//                    slot.getReservation_id() != null ? slot.getReservation_id() : slotFromDBById.getReservation_id());
//            preparedStatement.setTime(5,
//                    slot.getTime_slot() != null ? slot.getTime_slot() : slotFromDBById.getTime_slot());
//            preparedStatement.setLong(6,
//                    slot.getSlot_id() != null ? slot.getSlot_id() : slotFromDBById.getSlot_id());
//
//            preparedStatement.executeUpdate();
//
//            preparedStatement.close();
//
//            logger.info("End of Slot update method");
//
//            return findOne(slot.getSlot_id());
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("SQL Issues!");
//        }
//    }

//    @Override
//    public Long delete(Long slot_id) {
//        logger.info("Start of Slot delete method");
//        List<Long> slotIDList = new ArrayList<>();
//
//        final String deleteSlotQuery = "delete from slots where slot_id = ?";
//
//        final String allSlotsIDQuery = "select slot_id from slots";
//
//        registerDriver();
//
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(deleteSlotQuery)
//        ) {
//            preparedStatement.setLong(1, slot_id);
//            preparedStatement.executeUpdate();
//
//            PreparedStatement allSlotsIDPreparedStatement = connection.prepareStatement(allSlotsIDQuery);
//            ResultSet rs = allSlotsIDPreparedStatement.executeQuery();
//
//            while (rs.next()) {
//                slotIDList.add(rs.getLong(1));
//            }
//
//            rs.close();
//            allSlotsIDPreparedStatement.close();
//            preparedStatement.close();
//
//            if (!slotIDList.contains(slot_id)) {
//                return slot_id;
//            } else throw new EntityNotDeletedException("Slot id# " + slot_id + " was not deleted.");
//
//        } catch (SQLException ex) {
//            logger.error(ex.getMessage(), ex);
//            throw new RuntimeException("SQL Issues!");
//        } finally {
//            logger.info("End of Slot delete method");
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

    private Slot parseResultSet(ResultSet rs) {

        Slot slot;

        try {
            slot = Slot.builder()
                    .slot_id(rs.getLong(SLOT_ID))
                    .restaurant_id(rs.getLong(RESTAURANT_ID))
                    .day_calendar(rs.getDate(DAY_CALENDAR))
                    .time_slot(rs.getTime(TIME_SLOT))
                    .is_available(rs.getBoolean(IS_AVAILABLE))
                    .reservation_id(rs.getLong(RESERVATION_ID))
                    .build();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return slot;
    }

    @Override
    public List<Slot> findAll() {
        return null;
    }
}