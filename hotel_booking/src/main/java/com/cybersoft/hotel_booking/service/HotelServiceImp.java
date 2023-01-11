package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.HotelEntity;
import com.cybersoft.hotel_booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImp implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    //CREATE
    @Override
    public HotelEntity addHotel(HotelEntity hotelEntity) {
        if (hotelEntity != null) {
            return hotelRepository.save(hotelEntity);
        }
        return null;
    }

    //READ
    @Override
    public List<HotelEntity> findAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public HotelEntity findHotelById(int id) {
        return hotelRepository.findById(id).orElse(null);
    }

    //UPDATE
    @Override
    public HotelEntity updateHotel(int id, HotelEntity hotelEntity) {
        if (hotelEntity != null) {
            HotelEntity hotelEntityFromDB = hotelRepository.findById(id).orElse(null);
            if (hotelEntityFromDB != null) {
                hotelEntityFromDB.setHotelName(hotelEntity.getHotelName());
                hotelEntityFromDB.setAddress(hotelEntity.getAddress());
                hotelEntityFromDB.setEmail(hotelEntity.getEmail());
                hotelEntityFromDB.setPhone(hotelEntity.getPhone());
                hotelEntityFromDB.setHotelRank(hotelEntity.getHotelRank());
                hotelEntityFromDB.setDescription(hotelEntity.getDescription());
                hotelEntityFromDB.setImage(hotelEntity.getImage());
                hotelEntityFromDB.setCity(hotelEntity.getCity());
                hotelEntityFromDB.setHotelServiceEntitySet(hotelEntity.getHotelServiceEntitySet());
                hotelEntityFromDB.setAttractionEntitySet(hotelEntity.getAttractionEntitySet());
                hotelEntityFromDB.setReviewEntitySet(hotelEntity.getReviewEntitySet());
                hotelEntityFromDB.setRoomEntitySet(hotelEntity.getRoomEntitySet());
                return hotelRepository.save(hotelEntityFromDB);
            }
        }
        return null;
    }

    //DELETE
    @Override
    public boolean deleteHotelById(int id) {
        HotelEntity hotelEntityFromDB = hotelRepository.findById(id).orElse(null);
        if (hotelEntityFromDB != null) {
            hotelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
