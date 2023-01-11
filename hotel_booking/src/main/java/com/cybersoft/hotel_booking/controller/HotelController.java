package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.entity.HotelEntity;
import com.cybersoft.hotel_booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //CREATE
    @PostMapping("/add")
    public ResponseEntity<Object> addHotel(@RequestBody HotelEntity hotelEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//CODE:400
        }

        HotelEntity hotelEntity1 = hotelService.addHotel(hotelEntity);

        return new ResponseEntity<>(hotelEntity1, HttpStatus.CREATED);//CODE:201
    }

    //READ
    @GetMapping("/get")
    public ResponseEntity<List<HotelEntity>> findAllHotel() {
        List<HotelEntity> hotelEntityList = hotelService.findAllHotel();

        if (hotelEntityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//CODE:204
        }

        return new ResponseEntity<>(hotelEntityList, HttpStatus.OK);//CODE:200
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> findHotelById(@PathVariable("id") Integer id) {
        HotelEntity hotelEntity = hotelService.findHotelById(id);

        if (hotelEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//CODE:404
        }

        return new ResponseEntity<>(hotelEntity, HttpStatus.OK);//CODE:200
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateHotel(@PathVariable("id") Integer id, @RequestBody HotelEntity hotelEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//CODE:400
        }

        HotelEntity hotelEntity1 = hotelService.updateHotel(id, hotelEntity);

        if (hotelEntity1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//CODE:404
        }

        return new ResponseEntity<>(hotelEntity1, HttpStatus.OK);//CODE:200
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteHotelById(@PathVariable("id") Integer id) {
        boolean success = hotelService.deleteHotelById(id);

        if (!success) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//CODE:404
        }

        return new ResponseEntity<>(HttpStatus.OK);//CODE:200
    }
}
