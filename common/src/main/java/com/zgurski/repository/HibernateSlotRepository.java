package com.zgurski.repository;

import com.zgurski.domain.HibernateSlot;

import java.util.List;

public interface HibernateSlotRepository extends CRUDRepository<Long, HibernateSlot> {

   List<HibernateSlot> findAll();
}
