package com.azgurski.service;

import com.azgurski.domain.Slot;
import com.azgurski.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;

    public SlotServiceImpl(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public Slot findOne(Long slot_id) {
        return slotRepository.findOne(slot_id);
    }

    @Override
    public List<String> showAvailableSlotsDayRestaurant(Date date, Long restaurant_id) {
        return slotRepository.showAvailableSlotsRestaurant(date, restaurant_id);
    }

    @Override
    public Slot create(Slot slot) {
        return slotRepository.create(slot);
    }

    @Override
    public Slot update(Slot slot) { return slotRepository.update(slot);}

    @Override
    public Long delete(Long slot_id) {
        return slotRepository.delete(slot_id);
    }
}