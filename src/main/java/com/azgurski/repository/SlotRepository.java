package com.azgurski.repository;

import com.azgurski.domain.Slot;

import java.sql.Date;
import java.util.List;

public interface SlotRepository extends CRUDRepository<Long, Slot>{

    Slot create(Slot slot);

    Slot findOne(Long slot_id);

    Slot update(Slot slot);

    Long delete(Long slot_id);

    List<String> showAvailableSlotsRestaurant(Date date, Long restaurant_id);
}