package com.example.newbusbookingsystem.Controller;

import com.example.newbusbookingsystem.Model.AdminModel;
import com.example.newbusbookingsystem.Model.BusModel;
import com.example.newbusbookingsystem.Model.PayloadClass;
import com.example.newbusbookingsystem.Service.AdminService;
import com.example.newbusbookingsystem.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    BusService busService;

    @GetMapping("/getalladmin")
    public List<AdminModel> getAllAdmin(){
        return adminService.getAllAdmin();
    }

    @GetMapping("/getadminbyid/{id}")
    public AdminModel getAdminById(@PathVariable("id") Long id){
        return adminService.findById(id);
    }

    @PostMapping("/addadmin")
    public AdminModel addAdmin(@RequestBody AdminModel adminModel){
        return adminService.addAdmin(adminModel);
    }

    @GetMapping("/verifyadmin")
    public String verifyAdmin(@RequestBody AdminModel model){
        if(adminService.verifyAdmin(model)){
            return "Admin Verified Successfully";
        }
        return "Admin Not Verified!!";
    }

    @PostMapping("/addbusdetails")
    public String addBusDetails(@RequestBody PayloadClass obj){
        //verifying admin
        if(adminService.verifyAdmin(obj.getAdminModel())){
            busService.addBusDetails(obj.getBusModel());
            return "Bus Details added Successfully";
        }

        return "Admin Not Verified !!";

    }

    @PutMapping("/updatename/{bid}/{name}")
    public String modifyName(@PathVariable("bid") Long bid, @PathVariable("name") String name,@RequestBody AdminModel adminModel
    ){
        if(adminService.verifyAdmin(adminModel)){
            boolean flag = busService.modifyName(bid,name);
            if(flag){
                return "Name updated successfully";
            }
        }

        return "Named not updated error occured!!";
    }

    @DeleteMapping("/deleteBus/{bid}")
    public String deleteBus(@RequestBody AdminModel adminModel, @PathVariable("bid") Long bid){
        if(adminService.verifyAdmin(adminModel)){
            boolean flag = busService.deleteBus(bid);
            if(flag){
                return "Bus deleted successfully";
            }
        }

        return "Error occured while deleting the bus!!";
    }




    


}
