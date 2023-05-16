package com.zgurski.controller.rest.springdata;

import com.zgurski.controller.exceptions.IllegalRequestException;
import com.zgurski.controller.hateoas.RestaurantModelAssembler;
import com.zgurski.controller.requests.HibernateRestaurantCreateRequest;
import com.zgurski.controller.requests.HibernateRestaurantUpdateRequest;
import com.zgurski.controller.requests.HibernateRestaurantSearchCriteria;
import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import com.zgurski.exception.EntityNotFoundException;
import com.zgurski.repository.HibernateRestaurantRepository;
import com.zgurski.repository.springdata.RestaurantDataRepository;
import com.zgurski.repository.springdata.RoleDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/rest/springdata/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "Restaurant management API")
public class RestaurantDataController {
    private final RestaurantDataRepository repository;

    private final HibernateRestaurantRepository hibernateRestaurantRepository;

    private final RoleDataRepository roleDataRepository;

    private final ConversionService conversionService;

    private final RestaurantModelAssembler assembler;

    @Value("${spring.data.rest.default-page-size}")
    private int size;

    @RestaurantGetAllSwagger
    @GetMapping("/all")
    public ResponseEntity<Object> getAllRestaurants() {
        List<HibernateRestaurant> restaurants = repository.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @RestaurantGetAllSwagger
    @GetMapping("/hateoas/all")
    public CollectionModel<EntityModel<HibernateRestaurant>> getAllHateoasRestaurants() {

        List<EntityModel<HibernateRestaurant>> restaurants = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantDataController.class)).withSelfRel());
    }

    @RestaurantSearchByIdSwagger
    @GetMapping("/hateoas/{id}")
    public EntityModel<HibernateRestaurant> getRestaurantById(@PathVariable("id") long id) {
        return assembler.toModel(hibernateRestaurantRepository.findOne(id));
    }



    //    (isolation = Isolation.DEFAULT не прописывается, noRollbackFor = Exception.class убивает консистентность
    // Самый простой исправить это — заменить Exception на непроверяемое исключение. Например, NullPointerException, но лучше своё или список исключений. Либо можно переопределить атрибут rollbackFor у аннотации.
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) //над create, update, delete
    @PostMapping
    public ResponseEntity<Object> saveRestaurant(
            @Valid @RequestBody HibernateRestaurantCreateRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        HibernateRestaurant hibernateRestaurant = conversionService.convert(request, HibernateRestaurant.class);

        hibernateRestaurant = repository.save(hibernateRestaurant);
        return new ResponseEntity<>(hibernateRestaurant, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateRestaurant(@Valid @RequestBody HibernateRestaurantUpdateRequest request) {

        HibernateRestaurant hibernateRestaurant = conversionService.convert(request, HibernateRestaurant.class);

        //TODO FindById возвращает Optional, временное решение ниже
//        HibernateRestaurant one = repository.findById(request.getRestaurant_id())
//                .orElseThrow(EntityNotFoundException::new);

        hibernateRestaurant = repository.save(hibernateRestaurant);

        return new ResponseEntity<>(hibernateRestaurant, HttpStatus.OK);
    }

    @RestaurantSearchByCountryAndCapacitySwagger
    @GetMapping("/search")
    public ResponseEntity<Object> searchRestaurantByCountryAndCapacity(
            @Parameter(hidden = true)
            // чтобы спрятать, в том числе Principal, возле папки, которую не хотим, чтобы попадала в ui
            @Valid @ModelAttribute HibernateRestaurantSearchCriteria criteria,
            BindingResult bindingResult) {
        System.out.println(bindingResult); // TODO throw new


//        List<HibernateRestaurant> searchList = repository.findByCountryAndCapacity(criteria.getCountry(), criteria.getCapacity());

        String resultString = "by country = " + criteria.getCountry() + ", by capacity = " + criteria.getCapacity();

        Map<String, List<HibernateRestaurant>> countryCapacityRestaurants =
                Collections.singletonMap(resultString, repository.findByCountryAndCapacity(criteria.getCountry(), criteria.getCapacity()));

        return new ResponseEntity<>(Collections.singletonMap("restaurants", countryCapacityRestaurants), HttpStatus.OK);
    }

    //Cache
    @GetMapping("/search_by_capacity/{capacity}")
    public ResponseEntity<Object> searchRestaurantByCapacity(
            @PathVariable("capacity") String capacityToSearch) {
//        System.out.println(bindingResult); // TODO throw new

        try {
            Capacity capacity = Capacity.valueOf(capacityToSearch.toUpperCase());
            String resultString = "restaurants by capacity = " + capacityToSearch;

            Map<String, List<HibernateRestaurant>> capacityRestaurants =
                    Collections.singletonMap(resultString, repository.findByCapacity(capacity));

            return new ResponseEntity<>(capacityRestaurants, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("Restaurant with requested capacity not found.");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<Object> testSpringData() {

        List<HibernateRestaurant> result = repository.findByNameAndCapacity("S'Musauer", Capacity.MIDDLE);

        return new ResponseEntity<>(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data Restaurants Search With Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Restaurants",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> testEndPoint(
            @Parameter(name = "page", example = "1", required = true)
            @PathVariable("page") int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(page, size))
        ), HttpStatus.OK);
    }


}

