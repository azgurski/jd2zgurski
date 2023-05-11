package com.zgurski.controller.mvc;

import com.zgurski.controller.exceptions.BillingDataNotFoundException;
import com.zgurski.domain.BillingData;
import com.zgurski.service.BillingDataServiceImpl;
import com.zgurski.service.RestaurantServiceImpl;
import com.zgurski.utils.BillingDataValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("/billing_data")
public class BillingDataController {

    private final BillingDataServiceImpl billingDataService;

    private final RestaurantServiceImpl restaurantService;

    private final BillingDataValidator billingDataValidator;

    @GetMapping
    public String index(Model model) {
        return "billing_data/index";
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable("id") String id, Model model) {
        Long parsedBillingDataId;

        try {
            parsedBillingDataId = Long.parseLong(id); //TODO try-catch
            BillingData billingData = billingDataService.findOne(parsedBillingDataId);
            model.addAttribute("billingData", billingData);
            return "billing_data/show";
        } catch (IllegalArgumentException ex) {
            throw new BillingDataNotFoundException("Error! The id=[" + id + "] you are looking for doesn't match an integer.");
        } catch (EmptyResultDataAccessException ex) {
            throw new BillingDataNotFoundException("Error! Billing_data with id=[" + id + "] doesn't exist.");
        }
    }

    @GetMapping("/new")
    public String fillNewPerson(Model model, @ModelAttribute("billing_data") BillingData billingData) {
        model.addAttribute("restaurants_list", restaurantService.findAll());
        return "billing_data/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("billing_data") @Valid BillingData billingData, BindingResult bindingResult, Model model) {
        billingDataValidator.validate(billingData, bindingResult);

        if (bindingResult.hasErrors()) {
            return "billing_data/new";
        }

        BillingData newBillingData = billingDataService.create(billingData);
        model.addAttribute("billingData", newBillingData);
        return "billing_data/success";
    }
}