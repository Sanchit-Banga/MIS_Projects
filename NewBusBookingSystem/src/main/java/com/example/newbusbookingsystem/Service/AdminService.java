package com.example.newbusbookingsystem.Service;

import com.example.newbusbookingsystem.Model.AdminModel;
import com.example.newbusbookingsystem.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    
    @Autowired
    AdminRepository adminRepository;
    
    public List<AdminModel> getAllAdmin(){
        return adminRepository.findAll();
    }

    public AdminModel findById(Long id) {
        return adminRepository.findById(id).get();
    }

    public AdminModel addAdmin(AdminModel adminModel) {
        return adminRepository.save(adminModel);
    }

    public boolean verifyAdmin(AdminModel model){
        Optional<AdminModel> obj = adminRepository.findById(model.getAdminId());

        if(obj == null || obj.isEmpty()){
            return false;
        }

        if(obj.get().getPasscode().equals(model.getPasscode())){
            return true;
        }

        return false;

    }



}
