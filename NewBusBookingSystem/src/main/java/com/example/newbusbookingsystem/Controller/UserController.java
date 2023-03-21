package com.example.newbusbookingsystem.Controller;

import com.example.newbusbookingsystem.Model.BusModel;
import com.example.newbusbookingsystem.Model.SeatBooking;
import com.example.newbusbookingsystem.Model.UserModel;
import com.example.newbusbookingsystem.Repository.BusRepository;
import com.example.newbusbookingsystem.Service.BusService;
import com.example.newbusbookingsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;



    @GetMapping("/getsrcdst/{src}/{dest}")
    public List<BusModel> getSrcDst(@PathVariable("src") String src, @PathVariable("dest") String dest){
        return userService.findAllBySrcDst(src, dest);
    }

    @GetMapping("/getseatdetail/{bid}")
    public String getSeatDetail(@PathVariable("bid") Long bid){
        Optional<BusModel> obj = userService.findById(bid);
        return userService.getSeatDetail(obj);

    }

    @PutMapping("/bookseat")
    public String seatBook(@RequestBody SeatBooking seatBooking){
        return userService.seatBook(seatBooking);
    }

    @DeleteMapping("/cancelseat")
    public String cancelSeat(@RequestBody SeatBooking seatBooking){
        return userService.cancelSeat(seatBooking);
    }



}
