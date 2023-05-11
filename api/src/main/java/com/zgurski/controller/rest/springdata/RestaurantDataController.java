package com.zgurski.controller.rest.springdata;

import com.zgurski.controller.exceptions.IllegalRequestException;
import com.zgurski.controller.requests.HibernateRestaurantCreateRequest;
import com.zgurski.controller.requests.HibernateRestaurantUpdateRequest;
import com.zgurski.controller.requests.RestaurantSearchCriteria;
import com.zgurski.domain.AuthenticationInfo;
import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import com.zgurski.exception.EntityNotFoundException;
import com.zgurski.repository.springdata.RestaurantDataRepository;
import com.zgurski.util.RestaurantFieldsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/springdata/restaurants")
@RequiredArgsConstructor
public class RestaurantDataController {
    private final RestaurantDataRepository repository;

    private final RestaurantFieldsGenerator generator;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRestaurants() {
        List<HibernateRestaurant> restaurants = repository.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveRestaurant(
            @Valid @RequestBody HibernateRestaurantCreateRequest request,
            BindingResult bindingResult) {

        //TODO Spring Converter : request -> entity

        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        HibernateRestaurant restaurant = HibernateRestaurant.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .streetHouse(request.getStreet_house())
                .city(request.getCity())
                .postcode(request.getPostcode())
                .country(request.getCountry())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .imageURL(request.getImage_URL())
                .roleId(Long.parseLong(request.getRole_id()))
                .website(request.getWebsite())
//                .emailUserAuth(generator.generateEmail(request.getPostcode()))
//                .passwordUserAuth(generator.generatePassword())
                .capacity(Capacity.NOT_SELECTED)
                .build();

        String generatedEmail = generator.generateEmail(request.getPostcode());
        String generatedPassword = generator.generatePassword();

        AuthenticationInfo info = new AuthenticationInfo(generatedEmail, generatedPassword);

        restaurant.setAuthenticationInfo(info);

        restaurant = repository.save(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateRestaurant(
            @RequestBody HibernateRestaurantUpdateRequest request) {


        //TODO Spring Converter : request -> entity
        //HibernateRestaurant restaurant = converterService.convert(request, HibernateRestaurant.class);

        //TODO FindById возвращает Optional, временное решение ниже
        HibernateRestaurant one = repository.findById(request.getRestaurant_id())
                .orElseThrow(EntityNotFoundException::new);

        one.setRestaurantId(request.getRestaurant_id());
        one.setName(request.getName());
        one.setEmail(request.getEmail());
        one.setPhone(request.getPhone());
        one.setStreetHouse(request.getStreet_house());
        one.setCity(request.getCity());
        one.setPostcode(request.getPostcode());
        one.setCountry(request.getCountry());
        one.setLongitude(request.getLongitude());
        one.setLatitude(request.getLatitude());
        one.setImageURL(request.getImage_URL());
        one.setRoleId(Long.parseLong(request.getRole_id()));
        one.setWebsite(request.getWebsite());
//      one.set.emailUserAuth(generator.generateEmail(request.getPostcode()))
//      one.set.passwordUserAuth(generator.generatePassword())
        one.setCapacity(Capacity.valueOf(request.getCapacity()));

        one = repository.save(one);

        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUserByNameCountry(
            @Valid @ModelAttribute RestaurantSearchCriteria criteria,
            BindingResult bindingResult) {
        System.out.println(bindingResult); // TODO throw new

        List<HibernateRestaurant> searchList = Collections.emptyList(); // TODO
//                repository.searchRestaurant(criteria.getQuery(), criteria.getCountry());

        return new ResponseEntity<>(Collections.singletonMap("restaurants", searchList), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> testSpringData() {

        List<HibernateRestaurant> result = repository.findByNameAndCapacity("S'Musauer", Capacity.MIDDLE);

        return new ResponseEntity<>(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Object> testEndPoint(@PathVariable("page") int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(page, 2))
                ), HttpStatus.OK);

        // Size приходит из конфигурации, отправляется в application yml, default-page-size и вычитать оттуда
    }

}

// List<HibernateRestaurant> findByCountryOrderByCountryAsc(String country);
//
//    List<HibernateRestaurant> findByNameAndCapacity(String name, Capacity capacity);
//
//    @Query("select r from HibernateRestaurant r")
//    List<HibernateRestaurant> findRestaurants();
//    List<HibernateRestaurant> result = repository.findRestaurants();
//
//    @Query(value = "select r from HibernateRestaurant r where r.country = :country and r.city = :city")
//    List<HibernateRestaurant> findByHQLQuery(String country, @Param("city") String city);
//              List<HibernateRestaurant> result = repository.findByHQLQuery("Germany", "Munich");