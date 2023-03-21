package com.example.newbusbookingsystem.Controller;

import com.example.newbusbookingsystem.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bus")
public class BusController {
    @Autowired
    BusService busService;




}
