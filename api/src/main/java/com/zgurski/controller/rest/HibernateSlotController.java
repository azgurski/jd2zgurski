package com.zgurski.controller.rest;

import com.zgurski.domain.HibernateSlot;
import com.zgurski.service.HibernateSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/slots")
@RequiredArgsConstructor
public class HibernateSlotController {

    private final HibernateSlotService slotService;

     @GetMapping
        public ResponseEntity<Object> getAllSlots() {
            List<HibernateSlot> slots = slotService.findAll();
            return new ResponseEntity<>(slots, HttpStatus.OK);
        }
}
