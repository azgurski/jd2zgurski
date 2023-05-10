package com.azgurski.repository;

import com.azgurski.domain.HibernateSlot;

import java.util.List;

public interface HibernateSlotRepository extends CRUDRepository<Long, HibernateSlot> {

   List<HibernateSlot> findAll();
}
