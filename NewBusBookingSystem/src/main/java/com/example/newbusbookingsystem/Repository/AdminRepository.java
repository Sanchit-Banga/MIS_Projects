package com.example.newbusbookingsystem.Repository;

import com.example.newbusbookingsystem.Model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel,Long> {

}
