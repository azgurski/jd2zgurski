package com.azgurski.service;

import com.azgurski.domain.Slot;

import java.sql.Date;
import java.util.List;

public interface SlotService {

    Slot findOne(Long slot_id);
    List<String> showAvailableSlotsDayRestaurant(Date date, Long restaurant_id);

    Slot create(Slot slot);
    Slot update(Slot slot);
    Long delete(Long slot_id);
}
