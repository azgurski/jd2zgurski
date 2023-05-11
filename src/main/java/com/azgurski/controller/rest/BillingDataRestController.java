package com.azgurski.controller.rest;

import com.azgurski.controller.exceptions.BillingDataNotCreatedException;
import com.azgurski.controller.exceptions.BillingDataNotFoundException;
import com.azgurski.controller.requests.BillingDataCreateRequest;
import com.azgurski.controller.requests.BillingDataUpdateRequest;
import com.azgurski.domain.BillingData;
import com.azgurski.service.BillingDataService;
import com.azgurski.util.BillingDataCreateRequestValidator;
import com.azgurski.util.BillingDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/billing_data")
@RequiredArgsConstructor
public class BillingDataRestController {

    private final BillingDataService billingDataService;

    private final BillingDataCreateRequestValidator billingDataCreateRequestValidator;



    @GetMapping("/all")
    public ResponseEntity<Object> findAllBillingData() {
        try {
            List<BillingData> billingDataList = billingDataService.findAll();
            return new ResponseEntity<>(billingDataList, HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            throw new BillingDataNotFoundException("Error! No billing data were found.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findBillingDataById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(billingDataService.findOne(Long.parseLong(id)), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            throw new BillingDataNotFoundException("Error! The id=[" + id + "] you are looking for doesn't match an integer.");
        } catch (EmptyResultDataAccessException ex) {
            throw new BillingDataNotFoundException("Error! Billing_data with id=[" + id + "] doesn't exist.");
        }
    }

    @PostMapping
    public ResponseEntity<BillingData> createBillingData(@RequestBody @Valid BillingDataCreateRequest request,
                                                         BindingResult bindingResult) {

            // TODO JSON parse error while entering the letters while digits expected JSON Valid Object Mapper.

        billingDataCreateRequestValidator.validate(request, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = getErrorMessageByFields(bindingResult);
            throw new BillingDataNotCreatedException(errorMessage.toString());
        }

        BillingData createdBillingData = BillingData.builder()
                    .restaurant_id(request.getRestaurant_id())
                    .iban(request.getIban())
                    .bic(request.getBic())
                    .role_id(request.getRole_id())
                    .build();

            BillingData createdBillingDataFromDB = billingDataService.create(createdBillingData);
            return new ResponseEntity<>(createdBillingDataFromDB, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillingData> updateBillingData(
            @PathVariable("id") String id,
            @RequestBody @Valid BillingDataUpdateRequest request,
            BindingResult bindingResult) {

        Long parsedId = Long.parseLong(id);

        try {
            billingDataService.findOne(parsedId);
        } catch (EmptyResultDataAccessException ex) {
            throw new BillingDataNotFoundException("Billing data with this id=["+ id + "] wasn't found and your request wasn't updated.");
        }

        //TODO validation fields (exception of cast, validation BD update request)

//        billingDataCreateRequestValidator.validate(request, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMessage = getErrorMessageByFields(bindingResult);
//            throw new BillingDataNotCreatedException(errorMessage.toString());
//        }

        BillingData billingDataToUpdate = BillingData.builder()
                .billing_data_id(parsedId)
                .restaurant_id(request.getRestaurant_id())
                .iban(request.getIban())
                .bic(request.getBic())
                .role_id(request.getRole_id())
                .build();

        BillingData updatedBillingData = billingDataService.update(billingDataToUpdate);

        return new ResponseEntity<>(updatedBillingData, HttpStatus.OK);
    }



    //кастомизация errorMessage
    private static StringBuilder getErrorMessageByFields(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();

        for (FieldError error : errors) {
            errorMessage.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage())
                    .append("; ");
        }
        return errorMessage;
    }
}