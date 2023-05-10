package com.azgurski.service;

import com.azgurski.domain.HibernateSlot;

import java.util.List;

public interface HibernateSlotService {
    List<HibernateSlot> findAll();
}

