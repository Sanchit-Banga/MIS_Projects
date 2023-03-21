package com.example.newbusbookingsystem.Service;

import com.example.newbusbookingsystem.Model.BusModel;
import com.example.newbusbookingsystem.Model.SeatBooking;
import com.example.newbusbookingsystem.Model.UserModel;
import com.example.newbusbookingsystem.Repository.BusRepository;
import com.example.newbusbookingsystem.Repository.SeatBookingRepository;
import com.example.newbusbookingsystem.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BusService busService;

    @Autowired
    SeatBookingRepository seatBookingRepository;

    public List<BusModel> findAllBySrcDst(String src, String dest) {
        return busService.findAllBySrcDst(src,dest);
    }

    public Optional<BusModel> findById(Long bid) {
        return busService.findById(bid);
    }

    public boolean seatCheck(Long bid, int sno, Long userId){
        SeatBooking obj = userRepository.seatCheck(bid,sno,userId);
        if(obj==null){
            return false;
        }
        return true;
    }

    public String seatBook(SeatBooking seatBooking) {

        Optional<BusModel> obj = busService.findById(seatBooking.getBusId());

        if(obj.isEmpty() || obj==null){
            return "Your bus id is invalid";
        }

        boolean flag = seatCheck(seatBooking.getBusId(),seatBooking.getSeatNo(),seatBooking.getUserId());
        if(flag){
            return "Your seat cannot be booked as already occupied";
        }

        int newseat = obj.get().getSeats()-1;
        int newoccupied = obj.get().getOccupied()+1;
        obj.get().setSeats(newseat);
        obj.get().setOccupied(newoccupied);
        busService.addBusDetails(obj.get());
        seatBookingRepository.save(seatBooking);
        return "Your seat booked successfully";
    }

    public String cancelSeat(SeatBooking seatBooking) {

        Optional<BusModel> obj = busService.findById(seatBooking.getBusId());

        if(obj.isEmpty() || obj == null){
            return "Your bus id is invalid";
        }

        boolean flag = seatCheck(seatBooking.getBusId(),seatBooking.getSeatNo(),seatBooking.getUserId());
        if(!flag){
            return "Your seat cannot be cancelled invalid process";
        }

        int newseat = obj.get().getSeats()+1;
        int newoccupied = obj.get().getOccupied()-1;
        obj.get().setSeats(newseat);
        obj.get().setOccupied(newoccupied);
        busService.addBusDetails(obj.get());
        seatBookingRepository.deleteSeat(seatBooking.getUserId(),seatBooking.getBusId(),seatBooking.getSeatNo());
        return "Your seat cancelled successfully";

    }


    public String getSeatDetail(Optional<BusModel> obj) {
        if(obj.isEmpty() || obj==null){
            return "No Bus available";
        }

        return obj.get().getSeats()+" seats available";
    }
}
