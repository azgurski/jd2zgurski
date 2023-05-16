package com.zgurski.repository.springdata;

import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import com.zgurski.domain.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;


public interface RestaurantDataRepository extends JpaRepository<HibernateRestaurant, Long>,
        PagingAndSortingRepository<HibernateRestaurant, Long>, CrudRepository<HibernateRestaurant, Long> {

    List<HibernateRestaurant> findByCountryAndCapacity(String country, Capacity capacity);

    List<HibernateRestaurant> findByNameAndCapacity(String name, Capacity capacity);

    List<HibernateRestaurant> findByRestaurantId(Long restaurant_id);

    Optional<HibernateRestaurant> findByEmail(String email);

    Boolean existsByEmail(String email);


    @Query("select r from HibernateRestaurant r")
    List<HibernateRestaurant> findRestaurants();

    @Query(value = "select r from HibernateRestaurant r where r.country = :country and r.city = :city")
    List<HibernateRestaurant> findByHQLQuery(String country, @Param("city") String city);

    //Cache
    @Cacheable("h_restaurants")
    List<HibernateRestaurant> findByCapacity(Capacity capacity);

//    @Query(value = "select rl from Role rl inner join HibernateRestaurant r on r.restaurantId = rl.h_restaurant.restaurantId" +
//            "")
//
//      "select * " +
//              " from roles " +
//              " inner join users u on u.id = roles.user_id" +
//              " where user_id = " + userId +
//            " order by roles.id desc";

//    @Query(value = "select r from Role r where r.h_restaurant = :restaurantIdToSearch")
//    List<Role> getRestaurantAuthorities(Long restaurantIdToSearch);
//

//    @Modifying
//    @Query(value = "insert into l_roles_restaurants(restaurant_id, role_id) values (:restaurant_id, :role_id)", nativeQuery = true)
//    int createRoleRow(@Param("restaurant_id") Long restaurantId, @Param("role_id") Long roleId);


}

// For Slot repository Set slot true, но не предпочтительно
//  @Modifying(flushAutomatically = true)
//  @Query(value = "update slots set is_available = true, nativeQuery = true)
//  void updateField;
//
// валидация на уровне сервиса