package com.zgurski.service;

import com.zgurski.domain.HibernateSlot;
import com.zgurski.repository.HibernateSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateSlotServiceImpl implements HibernateSlotService {
    private final HibernateSlotRepository hibernateSlotRepository;

    @Override
    public List<HibernateSlot> findAll() {
        return hibernateSlotRepository.findAll();
    }
}
