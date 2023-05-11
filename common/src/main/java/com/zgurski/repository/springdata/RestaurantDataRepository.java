package com.zgurski.repository.springdata;

import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RestaurantDataRepository extends JpaRepository<HibernateRestaurant, Long>,
        PagingAndSortingRepository<HibernateRestaurant, Long>, CrudRepository<HibernateRestaurant, Long> {

    List<HibernateRestaurant> findByCountryOrderByCountryAsc(String country);

    List<HibernateRestaurant> findByNameAndCapacity(String name, Capacity capacity);

    @Query("select r from HibernateRestaurant r")
    List<HibernateRestaurant> findRestaurants();

    @Query(value = "select r from HibernateRestaurant r where r.country = :country and r.city = :city")
    List<HibernateRestaurant> findByHQLQuery(String country, @Param("city") String city);
}

// For Slot repository Set slot true, но не предпочтительно
//  @Modifying(flushAutomatically = true)
//  @Query(value = "update slots set is_available = true, nativeQuery = true)
//  void updateField;
//
// валидация на уровне сервиса